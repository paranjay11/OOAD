#include <iostream>
#include <string>
#include <chrono>
#include <thread>
#include <vector>
#include <cctype>
/*

The concept of forward declaration in classes is shown below.
class A;
class B{
private:
    A a; // this will throw an error of incomplete type
};
class A{
private:
    int k;
};
This error occurs because though the forward declaration is present but to the compiler there is no information of the class A . i.e. the Size the variable, the attibutes , methods.
Hence one way out is

class A;
class B{
private:
    A *a; // this will work as it is a pointer to class A
};
class A{
private:
    int k;
};
the same is with the method of a forward declared classes function.

class A;
class B{
private:
    A *a;
public:
    void doSomethingB(){
        this->a->doSomethingA();// this will throw error because even if class A is forward declared
                                // the complier is pointing to class A but the compiler still does not know about the methods in class A
    }
};
class A{
private:
    int k;
public:
    A(int k){
        this->k;
    }
    void doSomethingA(){
        return k*k;
    }
}

*/

using namespace std;

class dogDoor;

bool compareChar(char & c1, char & c2)
{
if (c1 == c2)
return true;
else if (std::toupper(c1) == std::toupper(c2))
return true;
return false;
}
/*
* Case Insensitive String Comparision
*/
bool caseInSensStringCompare(std::string & str1, std::string &str2)
{
return ( (str1.size() == str2.size() ) &&
std::equal(str1.begin(), str1.end(), str2.begin(), &compareChar) );
}

class remote{
private:
    dogDoor *door;

public:
    remote(dogDoor *door){
        this->door=door;
    }
    void pressButton();
};


class Bark{
private:
    string sound;
public:
    Bark(string &str){
        this->sound=str;
    }
    string getSound(){
        return sound;
    }
    bool isEqual(Bark &otherBark){
        return caseInSensStringCompare(this->sound,otherBark.sound);
//        if(caseInSensStringCompare(this->sound,otherBark.sound)==0)
//            return true;
//        else
//            return false;
    }
};

class barkRecognizer{
private:
    dogDoor *door;
public:
    barkRecognizer(dogDoor *door){
        this->door=door;
    }
    void recognize(Bark &bark);
};

class dogDoor{
private:
    bool door;
    vector<Bark> allowedBarks;
public:
    dogDoor(){
        this->door=false;
    }
    void open(){
        cout<<"opening door...."<<endl;
        this->door=true;
        std::this_thread::sleep_for(std::chrono::seconds(5));
        this->close();
    }
    void close(){
        cout<<"closing door ....."<<endl;
        this->door=false;
    }
    bool isOpen(){
        return door;
    }

    vector<Bark> getAllowedBarks(){
        return allowedBarks;
    }

    void setAllowedBarks(Bark &bark){
        cout<<"new Bark ->"<<bark.getSound()<<" added !"<<endl;
        allowedBarks.push_back(bark);
    }

};
void remote::pressButton(){
        if(this->door->isOpen()){
            this->door->close();
        }
        else{
            this->door->open();
            // this is the part which need to go inside dogdoor
            /*std::this_thread::sleep_for(std::chrono::seconds(5));
            door.close();*/
        }
    }

void barkRecognizer::recognize(Bark &bark){
        vector<Bark> allowedBarks=this->door->getAllowedBarks();
        for(int i=0;i<allowedBarks.size();i++){
            if(allowedBarks[i].isEqual(bark)){
                cout<<"Dog recognized!"<<endl;
                this->door->open();
                return;
            }
        }
//        for(vector<Bark>::iterator it=allowedBarks.begin();it!=allowedBarks.end();it++){
//            if(it->isEqual(bark)){
//                cout<<"Dog recognized!!"<<endl;
//                this->door->open();
//                return;
//            }
//        }
        cout<<"This is not a recognized dog"<<endl;
    }


int main()
{
    dogDoor door;
    remote remcom(&door);
    barkRecognizer recognizer(&door);
    vector<Bark> kk;
    vector<string> ss{"Woof","woofff","oooo","RRuff"};
    for(int i=0;i<ss.size();i++){
//            cout<<"string ==="<<ss[i]<<endl;
            Bark mm(ss[i]);
            kk.push_back(mm);
    }

    for(int i=0;i<kk.size();i++){
            door.setAllowedBarks(kk[i]);
    }

    cout<<"Dog barks.....!"<<endl;
    Bark dummy=kk[2];
    recognizer.recognize(dummy);

    cout<<"Dog barks.....!"<<endl;
    string mk="uufop";
    Bark second(mk);
    recognizer.recognize(second);



    return 0;
}
