#include <bits/stdc++.h>

using namespace std;

class Station{
private:
    string name;
    int num;
public:

    Station(string name ,int num){
       // cout<<"constructor Name: "<<name<<" , num : "<<num<<endl;
        this->name=name;
        transform(this->name.begin(),this->name.end(),this->name.begin(), ::tolower);
        //cout<<"IN contructor Stations : "<<name<<endl;
        this->num=num;
    }
    string getStationName(){
        return this->name;
    }
    int getNum(){
        return this->num;
    }
    bool isEqual(Station obj){
        string str=obj.getStationName();
        transform(str.begin(),str.end(),str.begin(), ::tolower);
       // cout<<"isEqual >>>>> from :"<<str<<" to : "<<this->name<<endl;
//        cout<<"is Equal >>>>> stations[i]->getName() : "<<obj.getStationName()<< " , temp->getString() : "<<this->name<<endl;
        if(str==this->name){
            //cout<<"true++++++++++++"<<endl;
            return true;
        }
        else{
                //cout<<"false+++++++++++"<<endl;
            return false;
        }
    }
};

class Connection{
private:
    Station *source,*destination;
    int weight;
    string line;
public:
    Connection(Station *source,Station *destination,string lineName){
        this->source=source;
        this->destination=destination;
        this->line=lineName;
        this->weight=1;
    }
    string getLineName(){
        return this->line;
    }

    string getSourceName(){
        return this->source->getStationName();
    }

    int getSourceNum(){
        return this->source->getNum();
    }

    string getDestinationName(){
        return this->destination->getStationName();
    }

    int getDestinationNum(){
        return this->destination->getNum();
    }
    int getWeight(){
        return this->weight;
    }
};

class Subway{
private:
    vector<Station> stations;
    int numStation;
    int StationNumber;
    class Edge{
        public:
        vector<Connection*> neighbours;
    };
    Edge *edges;
    int *parent;
    int getMin(int *arr,bool *visited){
        int minVal=INT_MAX,index=-1;
        for(int i=0;i<this->numStation;i++){
            if(arr[i]<minVal && !visited[i]){
                minVal=arr[i];
                index=i;
            }
        }
        return index;
    }


      void dijkstra(int source){
        int *dist=new int[numStation];
        bool *visited=new bool[numStation];
        for(int i=0;i<numStation;i++){
            dist[i]=INT_MAX,visited[i]=false;
        }
        dist[source]=0;
        //cout<<"REached in dijkstra>>>>>"<<getMin(dist,visited)<<endl;
        for(int i=0;i<numStation;i++){
            int u=getMin(dist,visited);
            visited[u]=true;
        //cout<<"REached in dijkstra>>>>>"<<u<<endl;
            for(unsigned int k=0;k<edges[u].neighbours.size();k++){
                int v=edges[u].neighbours[k]->getDestinationNum();
                int vWeight=edges[u].neighbours[k]->getWeight();
//                string vString=edges[u].neighbours[k]->getDestinationName();
//                string uString=stations[u].getStationName();
                //cout<<" u: "<<uString<<" , v: "<<vString<<" , weight:"<<vWeight<<endl;
                if(visited[v]==false && (dist[u]+vWeight)<dist[v]){
                    dist[v]=dist[u]+vWeight;
                    //cout<<">>>>>>>>entered for u: "<<uString<<" ,v: "<<vString<<" , with weight : "<<dist[v]<<endl;
                    parent[v]=u;
                }
            }
        }
    }

    Connection *searchConnection(int src,int dest){
        for(unsigned int i=0;i<edges[src].neighbours.size();i++){
            if(edges[src].neighbours[i]->getDestinationNum()==dest){
                return edges[src].neighbours[i];
            }
        }
        return NULL;
    }


    Station *hasStation(Station *obj){
        //cout<<"entered hasStation >>>>>>>> "<<"size() : "<<stations.size()<<endl;
        for(unsigned int i=0;i<this->stations.size();i++){
            if(obj->isEqual(this->stations[i])){
              //  cout<<"has station"<<endl;
                return &this->stations[i];
            }
        }
        return obj;
    }

public:
    Subway(int num){
        //cout<<"got into costructor of subway"<<endl;
        numStation=num;
        edges=new Edge[num];
        StationNumber=0;

        parent=new int[numStation];
    }

    void printRoute(string source,string destination){
        int src=hasStation(new Station(source,-1))->getNum();
        int dest=hasStation(new Station(destination,-1))->getNum();
        cout<<"src : " <<src<<"  ,dest : "<<dest<<endl;
        if(src != -1 && dest!=-1){
           dijkstra(src);
           cout<<"+++++++++++++++++++"<<endl;
           stack<int> arr;
           int i=dest;
           arr.push(dest);
           while(parent[i]!=src){
                arr.push(parent[i]);
                i=parent[i];
           }
           vector<Connection*> vec;
           arr.push(src);
           while(!arr.empty()){
                int a=arr.top();
                arr.pop();
                if(!arr.empty()){
                    int b=arr.top();
                    vec.push_back(searchConnection(a,b));
                }
           }

           for(unsigned int i=0;i<vec.size();i++){
                cout<<"Station from : "<< vec[i]->getSourceName() << " , Station to : "<<vec[i]->getDestinationName()<<" , on line : "<<vec[i]->getLineName()<<endl;
           }
        }
    }



    void addStation(string name){
        Station *tempStation=new Station(name,-1);
        Station *p = hasStation(tempStation);
       // cout<<"addStation >>>>>  "<<p->getStationName() << " , stationNumber : "<<p->getNum()<<endl;
        if(-1==p->getNum()){
            stations.push_back(Station(name,StationNumber));
            cout<<"created Station : "<<stations[StationNumber].getStationName()<<" , with number :" <<stations[StationNumber].getNum()<<endl;
            StationNumber++;
        }
        else{
            cout<<"Duplicate station>>>> : "<<p->getNum() <<endl;
        }
        delete(tempStation);
    }





   void addConnection(string source,string destination,string line){
       Station *src=hasStation(new Station(source,-1));
       Station *dest=hasStation(new Station(destination,-1));

       if(src->getNum() != -1 && dest->getNum()!=-1){
        edges[src->getNum()].neighbours.push_back(new Connection(src,dest,line));
        edges[dest->getNum()].neighbours.push_back(new Connection(dest,src,line));
        cout<<"connection Created....."<<endl;
        //cout<<"edge Source: "<<edges[src->getNum()].neighbours[0]->getSourceName() <<" destination : "<<edges[src->getNum()].neighbours[0]->getDestinationName()<<endl;
       }
   }


};




int main()
{
    cout << "Hello world!" << endl;

    Subway subway(13);
    subway.addStation("A");
    subway.addStation("B");
    subway.addStation("C");
    subway.addStation("D");
    subway.addStation("E");
    subway.addStation("F");
    subway.addStation("G");
    subway.addStation("H");
    subway.addStation("I");
    subway.addStation("J");
    subway.addStation("K");
    subway.addStation("L");
    subway.addStation("M");
    subway.addStation("e");
    cout<<endl;
    cout<<"......................."<<endl;
    subway.addConnection("A","B","ab");
    cout<<endl;
    subway.addConnection("B","C","ab");
    cout<<endl;
    subway.addConnection("D","C","ab");
    subway.addConnection("D","E","ab");
    subway.addConnection("F","G","pq");
    subway.addConnection("G","C","pq");
    subway.addConnection("C","H","pq");
    subway.addConnection("H","I","pq");
    subway.addConnection("I","J","pq");
    subway.addConnection("B","H","mn");
    subway.addConnection("H","E","mn");
    subway.addConnection("L","K","cd");
    subway.addConnection("M","E","cd");
    subway.addConnection("J","L","cd");
    subway.addConnection("E","J","cd");
    //subway.addConnection("B","C","ab");

    subway.printRoute("A","M");

    return 0;
}
