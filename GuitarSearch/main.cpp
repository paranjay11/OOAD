#include <iostream>
#include <string>
#include <cstring>
#include <vector>

using namespace std;
template<typename Base,typename T>
bool isInstanceOf(const T*){// T*???

    bool kk=is_base_of<Base,T>::value;
//    cout<<"inside of Instance of..."<<kk<<endl;
    return kk;
}


enum Builder{
    builder1,
    builder2,
    builder3,
    builder4
};

enum Wood{
    blackWood,
    teakWood,
    mapleWood,
    coloredWood
};

enum Type{
    electric,
    acoustic,
    semiAcoustic
};

enum MandolinType{
    type1,
    type2,
    type3,
    type4
};
class InstrumentSpecs{
private:
    Builder builder;
    Wood topWood;
    Wood backWood;
    string model;
    Type type;
public:
    InstrumentSpecs(Builder builder,Wood topWood,Wood backWood,Type type,string model){
        this->builder=builder;
        this->topWood=topWood;
        this->backWood=backWood;
        this->model=model;
        this->type=type;
    }
    Builder getBuilder(){
        return builder;
    }
    Wood getTopWood(){
        return topWood;
    }
    Wood getBackWood(){
        return backWood;
    }
    string getModel(){
        return model;
    }
    Type getType(){
        return type;
    }
    bool isEqual(InstrumentSpecs *specs){
        cout<<"Comparing InstrumentsSpec...."<<endl;
        if(builder!=specs->getBuilder())
            return false;
        if(topWood!=specs->getTopWood())
            return false;
        if(backWood!=specs->getBackWood())
            return false;
        if(type!=specs->getType())
            return false;

        return true;
    }

    virtual ~InstrumentSpecs()=default;// this is a big point .... this is somewhere related to conversion of parent class to child clas and reverse.....try removin gthis and understand the error which comes with it
   // virtual void dummy();
};

class MandolinSpec : public InstrumentSpecs{
private:
    MandolinType mandolinType;
public:
    MandolinSpec(MandolinType mandolinType,Builder builder,Wood topWood,Wood backWood,Type type,string model): InstrumentSpecs(builder,topWood,backWood,type,model){
        cout<<"MandolinSpec created!!!"<<endl;
        this->mandolinType=mandolinType;

    }
    int getMandolinType(){
        return mandolinType;
    }
    bool isEqual(MandolinSpec specs){
        cout<<"comparing with Mandolin Spec..."<<endl;
        if(isInstanceOf<MandolinSpec>(&specs)){
            if(mandolinType==specs.getMandolinType()){
                InstrumentSpecs *spec=&specs;
                if(InstrumentSpecs::isEqual(spec)){
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

};
class GuitarSpec : public InstrumentSpecs{
private:
    int numStrings;
public:
    GuitarSpec(int numString,Builder builder,Wood topWood,Wood backWood,Type type,string model):InstrumentSpecs(builder,topWood,backWood,type,model){
        cout<<"GuitarSpec created!!!"<<endl;
        this->numStrings=numString;

    }
    int getNumString(){
        return numStrings;
    }
    bool isEqual(GuitarSpec specs){
        cout<<"Comparing with GuitarSpec..."<<endl;
        if(isInstanceOf<GuitarSpec>(&specs)){
            if(numStrings==specs.getNumString()){
                    InstrumentSpecs *spec=&specs;
                if(InstrumentSpecs::isEqual(spec)){
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

};

class Instruments{
private:
    string serial;
    double price;
    InstrumentSpecs *specs;
public:
    Instruments(string serial,double price,InstrumentSpecs *specs){
        cout<<"Sepcs assigned..."<<endl;
        this->serial=serial;
        this->price=price;
        this->specs=specs;
    }
    //void setSpec(InstrumentSpec spec)=0;
    string getSerial(){
        return serial;
    }

    double getPrice(){
        return price;
    }
    InstrumentSpecs *getSpecs(){
        return specs;
    }

};
class Guitar:public Instruments{
public:
    Guitar(string serial,double price,GuitarSpec *specs):Instruments(serial,price,specs){
        cout<<"Guitar created!!!"<<endl;
    }
};

class Mandolin:public Instruments{
public:
    Mandolin(string serial,double price,MandolinSpec *specs):Instruments(serial,price,specs){
        cout<<"Mandolin Created!!!"<<endl;
    }
};


class Inventory{
private:
    vector<Instruments> instruments;
public:
    void addInstrument(string serial,double price,InstrumentSpecs *specs){
        cout<<"Adding instruments >>>> ";

        if(GuitarSpec *spec = dynamic_cast<GuitarSpec*>(specs)){
            cout<<"It is a Guitar...."<<endl;
            Guitar instrument=Guitar(serial,price,spec);// why didnt i do Instruments *instrument= new Guitar(...) & how is the present thing working. can we send an object of derived class and save it in base classes container.
            instruments.push_back(instrument);
            //return true;
        }
        else if(MandolinSpec *spec = dynamic_cast<MandolinSpec*>(specs)){
            cout<<"It is a Mandolin..."<<endl;
            Mandolin instrument=Mandolin(serial,price,spec);
            instruments.push_back(instrument);
            //return true;
        }
        cout<<"serial of first entry>> "<<instruments[0].getSerial()<<endl;
    }

    vector<Instruments> searchInstrument(InstrumentSpecs *specs){
        vector<Instruments> lst;
        cout<<"Searching..."<<endl;
        if(GuitarSpec *spec= dynamic_cast<GuitarSpec*>(specs)){
            cout<<"Search in Guitars...."<<endl;
            for(unsigned int i=0;i<instruments.size();i++){
                InstrumentSpecs *ptr=instruments[i].getSpecs();
                if(GuitarSpec *currentSpec = dynamic_cast<GuitarSpec*>(ptr)){// try if(specs.isEqual)// ye karne mai usse pehle instanceOf checkkarna padega
                    if(spec->isEqual(*currentSpec))
                    lst.push_back(instruments[i]);
                }
            }
        }
        else if(MandolinSpec *spec=dynamic_cast<MandolinSpec*>(specs)){
            cout<<"Search in Mandolins..."<<endl;
            for(unsigned int i=0;i<instruments.size();i++){
                InstrumentSpecs *ptr=instruments[i].getSpecs();
//                cout<<"isInstance of >>>"<<isInstanceOf<InstrumentSpecs>(ptr)<<endl;

                MandolinSpec *currentSpec = dynamic_cast<MandolinSpec*>(ptr);
//                cout<<"isInstanceOf >>>"<<isInstanceOf<MandolinSpec>(currentSpec)<<endl;
//                cout<<"ptr->backWood = "<<ptr->getBackWood()<<endl;
                if(currentSpec){// try if(specs.isEqual)// ye karne mai usse pehle instanceOf checkkarna padega
//                    cout<<"reaching here..."<<endl;
                    if(spec->isEqual(*currentSpec))
                    lst.push_back(instruments[i]);
                }
            }
        }


        return lst;
    }


};

void printList(vector<Instruments> &lst){
    cout<<"List of matched Instruments...."<<endl;
    for(unsigned int i=0;i<lst.size();i++){
        cout<<lst[i].getSerial()<<"\t"
            <<lst[i].getSpecs()->getModel()<<endl;
    }
}

int main()
{
    Inventory inventory;
    string kk="first mandolin";
    InstrumentSpecs *ptrSpecs= new MandolinSpec(type1,builder1,blackWood,teakWood,electric,kk);
   // cout<<isInstanceOf<GuitarSpec>(&mandoSpec1)<<endl;
    inventory.addInstrument("1234",1000,ptrSpecs);
    InstrumentSpecs *ptrSpecs2 = new MandolinSpec(type1,builder1,blackWood,teakWood,electric,"Second Mandolin");
    inventory.addInstrument("2345",2000,ptrSpecs2);

    vector<Instruments> lst=inventory.searchInstrument(ptrSpecs);
    printList(lst);
    cout << "Hello world!" << endl;
    return 0;
}
