#include <iostream>
#include <string>
#include <chrono>
#include <thread>
#include <vector>
#include <cctype>


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
        this->sound=sound;
    }
    string getSound(){
        return sound;
    }
    bool isEqual(Bark &otherBark){
        cout<<"this->sound: "<<this->sound<<endl;
        cout<<"otherBark.sound"<<otherBark.sound<<endl;
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
        cout<<"new Bark "<<bark.getSound()<<"added !"<<endl;
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
            kk.push_back(Bark(ss[i]));
    }

    for(int i=0;i<kk.size();i++){
            door.setAllowedBarks(kk[i]);
    }

    cout<<"Dog barks.....!"<<endl;
    Bark dummy=kk[2];
    recognizer.recognize(dummy);
//    string mk="uufop";
//    Bark second(mk);
//    recognizer.recognize(second);



    return 0;
}
