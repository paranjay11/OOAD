#include<bits/stdc++.h>

using namespace std;

enum BookStatus{
    reserved,
    available,
    unavailable,
    checkedOut
};

enum BookProperty{
    name,
    title,
    author,
    publicationDate,
    uid
};

class RackLocation{
private:
    int column;
    int row;
    int shelf;
public:
    bool operator !=( const RackLocation &other)const{
        if(this->column==other.getColumn() && this->row==other.getRow() && this->shelf==other.gerShelf() )
            return false;

        return true;
    }

    void setRackLocation(int r,int c,int s){
        this->column=c;
        this->row=r;
        this->shelf=s;
    }
    int getColumn(){
        return this->column;
    }
    int getRow(){
        return this->row;
    }
    int gerShelf(){
        return this->shelf;
    }
};

class DateFormat{
private:
    int day;
    int month;
public:
    bool operator != (const DateFormat &other)const{
        if(this->day==other.getDay() && this->month==other.getMonth()){
            return false;
        }
        return true;
    }

    int days(DateFormat d){
        if(d.getMonth()==this->month){
            return abs(d.getDay-this->day);
        }
        int k=30-this->day;
        k=k+d.getDay();
        int kk=d.getMonth()-(this->month+1);
        k=k+30*kk;
        return k;
    }

    int getDay(){
        return day;
    }

    int getMonth(){
        return month;
    }

    void setDate(int d,int m ){
        this->day=d;
        this->month=m;
    }


};

class Books{
private:
    map<BookProperty,string> properties;
    DateFormat issueDate;
    DateFormat dueDate;
    RackLocation rackLocation;
    BookStatus bookStatus;
    int price;
    int issuedToId;
    int reservedForId;
public:

    bool operator ==(const Books& other)const{
        map<BookProperty,string>::iterator it=properties.begin();
        auto k=other.getProperties();
        auto it2=k.begin();
        for( ;it!=properties.end() && it2!=k.end();it++ , it2++){
            if(*it!=*it2){
                return false;
            }
        }

        if(this->issueDate!=other.getIssueDate() && this->dueDate != other.getDueDate()){
            return false;
        }

        if(this->rackLocation!=other.getLocation())
            return false;

        if(this->bookStatus!=other.getBookStatus())
            return false;

        if(this->price!=other.getPrice())
            return false;

        if(this->issuedToId!=other.getIssuedToId())
            return false;

        if(this->reservedForId!=other.getReservedForId())
            return false;

        return true;

    }

    int getPrice(){
        return this->price;
    }
    map<BookProperty,string> getProperties(){
        return this->properties;
    }

    void setReservedFor(int id){
        this->reservedForId=id;
    }

    int getReservedForId(){
        return this->reservedForId;
    }

    void setIssuedToId(int a){
        this->issuedToId=a;
    }

    int getIssuedToId(){
        return this->issuedToId;
    }

    string getValue(BookProperty b){
        return properties[b];
    }

    void setValue(BookProperty b,string s){
        properties[b]=s;
    }

    void setPrice(int price){
        this->price=price;
    }

    void setIssueDate(int d,int m){
        this->issueDate.setDate(d,m);
        if(d>24){
            this->setDueDate(7-(30-d),m+1);
        }
        else{
            this->setDueDate(d+7,m);
        }
    }

    void setDueDate(int d,int m){
        this->dueDate.setDate(d,m);
    }

    DateFormat getIssueDate(){
        return this->issueDate;
    }

    DateFormat getDueDate(){
        return this->dueDate;
    }

     int getDueAmount(){
        return price*(this->issueDate.days(this->dueDate));
    }

    void setLocation(int r,int c,int s){
        this->rackLocation.setRackLocation(r,c,s);
    }

    string getLocation(){
        string r = to_string(this->rackLocation.getRow());
        string c = to_string(this->rackLocation.getColumn());
        string s = to_string(this->rackLocation.gerShelf());
        string loc="shelf : " +s+" , column : "+c+" , row : "+r;
        return loc;
    }

    void setBookStatus(BookStatus status){
        this->bookStatus=status;
    }

    BookStatus getBookStatus(){
        return this->bookStatus;
    }

};

class Account{
private:
    int id;
    string password;
    string name;
public:

    void setAccountDetails(string n,string p,int id){
        this->name=n;
        this->password=p;
        this->id=id;
    }
    bool isEqual(string name,string password){
        if(name==this->name && password==this->password)
            return true;
        return false;
    }

    bool isEqualName(string name){
        if(name==this->name)
            return true;
        return false;
    }
};



class Library{
public:
    map<string,vector<int>> titleBooks;
    map<string,vector<int>> authorBooks;
    vector<Books> books;
    map<int,Account> accounts;

//    bool isBookPresent(Books book){
//        for(int i=0;i<books.size();i++){
//            if(book==books[i])
//                return true;
//        }
//        return false;
//    }
};

class Librarian:public Account{
private:
    Library library;
public:
    Librearian(string n,string p,int id){
        this->setAccountDetails(n,p,id);
    }

    void addBook(Books book){
//        if(isBookPresent(Book)){
//            return ;
//        }
        library.books.push_back(book);
        library.titleBooks[book.getValue(title)].second->push_back(book.getValue(uid));
        library.authorBooks[book.getValue(author)].second->push_back(book.getValue(uid));
    }

    void removeBook(Books book){
        if(!isBookPresent(book))
            return ;

        vector<Books>::iterator it=find(books.begin(),books.end(),book);
        library.books.erase(it);

        vector<int>::iterator it2=library.titleBooks[book.getValue(title)].second.begin();
        vector<int>::iterator it3=library.titleBooks[book.getValue(title)].second.end();
        for( ;it2!=it3;++it2){
            if(book==*it2){
                library.titleBooks[book.getValue(title)].second.erase(it2);
                break;
            }
        }

        vector<int>::iterator it4=library.authorBooks[book.getValue(author)].second.begin();
        vector<int>::iterator it5=library.authorBooks[book.getValue(author)].second.end();
        for( ; it4!=it5;++it4){
            if(book==*it4){
                library.authorBooks[book.getValue(author)].second.erase(it4);
            }
        }

    }
};

class Member:public Account{
    Books books[5];
    int numOfBooks;
public:
    Member(string n,string p,int id){
        this->setAccountDetails(n,p,id);
        this->numOfBooks=0;
    }

    bool checkOutBook(Books book,DateFormat date){
        if(book.getBookStatus()==BookStatus.unavailable || book.getBookStatus()==BookStatus.checkedOut){
            return false;
        }
        else if(book.getBookStatus()==BookStatus.reserved){
            if(book.getReservedForId()!=this->id){
                return false;
            }
            else{
                book.setIssueDate(date);
                book.setBookStatus(checkedOut);
                book.setIssuedToId(this->id);
            }
        }
        else if(book.getBookStatus()==BookStatus.available){
                book.setIssueDate(date);
                book.setBookStatus(checkedOut);
                book.setIssuedToId(this->id);
        }
    }

    bool reserveBook(Books book){
        if(book.getBookStatus()==BookStatus.unavailable || book.getBookStatus()==BookStatus.checkedOut){
            return false;
        }
        else if(book.getBookStatus()==BookStatus.reserved){
            return false;
        }
        else if(book.getBookStatus()==BookStatus.available){
            book.setBookStatus(reserved);
            book.setReservedFor(this->id);
        }
    }

    int returnBook(Books book){
        book.setBookStatus(available);
        return book.getDueAmount();
    }

};







int main(){

    //Librarian librarian(paranjay,rickshaw,100);

    return 0;
}
