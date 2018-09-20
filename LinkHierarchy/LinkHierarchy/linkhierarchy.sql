
create database LinkTablesHierarchys;
use LinkTablesHierarchys;

create table Page_A ( 
page_A_Id int AUTO_INCREMENT PRIMARY KEY, 
page_A_Name varchar(20) not null 
);

insert into Page_A(page_A_Name) values('Investment_Banking');
insert into Page_A(page_A_Name) values('Retail_Banking');
select * from Page_C;

create table Page_B ( 
page_B_Id int AUTO_INCREMENT PRIMARY KEY, 
page_B_Name varchar(35) not null, 
page_A_Id int,
foreign key(page_A_Id) references Page_A(page_A_Id)
);

insert into Page_B(page_B_Name,page_A_Id) values('Corporate_Finance(Advisory)',(select page_A_Id from Page_A where page_A_Name='Investment_Banking'));
insert into Page_B(page_B_Name,page_A_Id) values('Capital_Market(Execution)',(select page_A_Id from Page_A where page_A_Name='Investment_Banking'));

insert into Page_B(page_B_Name,page_A_Id) values('Private_Banks',(select page_A_Id from Page_A where page_A_Name='Retail_Banking'));
insert into Page_B(page_B_Name,page_A_Id) values('Public_Banks',(select page_A_Id from Page_A where page_A_Name='Retail_Banking'));

create table Page_C ( 
page_C_Id int AUTO_INCREMENT PRIMARY KEY, 
page_C_Name varchar(35) not null, 
page_B_Id int,
foreign key(page_B_Id) references Page_B(page_B_Id)
);

insert into Page_C(page_C_Name,page_B_Id) values('Industry_Coverage',(select page_B_Id from Page_B where page_B_Name='Corporate_Finance(Advisory)'));
insert into Page_C(page_C_Name,page_B_Id) values('Merger&Acquisition',(select page_B_Id from Page_B where page_B_Name='Corporate_Finance(Advisory)'));

insert into Page_C(page_C_Name,page_B_Id) values('Equity_Capital_Market',(select page_B_Id from Page_B where page_B_Name='Capital_Market(Execution)'));
insert into Page_C(page_C_Name,page_B_Id) values('Debt_Capital_Market',(select page_B_Id from Page_B where page_B_Name='Capital_Market(Execution)'));



insert into Page_C(page_C_Name,page_B_Id) values('Deposits',(select page_B_Id from Page_B where page_B_Name='Private_Banks'));
insert into Page_C(page_C_Name,page_B_Id) values('Card_Services',(select page_B_Id from Page_B where page_B_Name='Private_Banks'));
insert into Page_C(page_C_Name,page_B_Id) values('Insurance',(select page_B_Id from Page_B where page_B_Name='Private_Banks'));

insert into Page_C(page_C_Name,page_B_Id) values('Personal_Banking',(select page_B_Id from Page_B where page_B_Name='Public_Banks'));
insert into Page_C(page_C_Name,page_B_Id) values('NRI_Services',(select page_B_Id from Page_B where page_B_Name='Public_Banks'));
insert into Page_C(page_C_Name,page_B_Id) values('Agricultural_Services',(select page_B_Id from Page_B where page_B_Name='Public_Banks'));

create table Page_D ( 
page_D_Id int AUTO_INCREMENT PRIMARY KEY, 
page_D_Name varchar(35) not null, 
page_C_Id int,
foreign key(page_C_Id) references Page_C(page_C_Id)
);

insert into Page_D(page_D_Name,page_C_Id) values('Healthcare',(select page_C_Id from Page_C where page_C_Name='Industry_Coverage'));
insert into Page_D(page_D_Name,page_C_Id) values('RealEstate',(select page_C_Id from Page_C where page_C_Name='Industry_Coverage'));
insert into Page_D(page_D_Name,page_C_Id) values('Energy',(select page_C_Id from Page_C where page_C_Name='Industry_Coverage'));
insert into Page_D(page_D_Name,page_C_Id) values('Joint_Ventures',(select page_C_Id from Page_C where page_C_Name='Merger&Acquisition'));
insert into Page_D(page_D_Name,page_C_Id) values('Split-Offs',(select page_C_Id from Page_C where page_C_Name='Merger&Acquisition'));


insert into Page_D(page_D_Name,page_C_Id) values('IPOs',(select page_C_Id from Page_C where page_C_Name='Equity_Capital_Market'));
insert into Page_D(page_D_Name,page_C_Id) values('Follow-On',(select page_C_Id from Page_C where page_C_Name='Equity_Capital_Market'));
insert into Page_D(page_D_Name,page_C_Id) values('Private_Placement',(select page_C_Id from Page_C where page_C_Name='Equity_Capital_Market'));

insert into Page_D(page_D_Name,page_C_Id) values('Investment_Grade',(select page_C_Id from Page_C where page_C_Name='Debt_Capital_Market'));
insert into Page_D(page_D_Name,page_C_Id) values('Leveraged_Finance',(select page_C_Id from Page_C where page_C_Name='Debt_Capital_Market'));

insert into Page_D(page_D_Name,page_C_Id) values('Current_Deposit',(select page_C_Id from Page_C where page_C_Name='Deposits'));
insert into Page_D(page_D_Name,page_C_Id) values('Recurring_Deposit',(select page_C_Id from Page_C where page_C_Name='Deposits'));
insert into Page_D(page_D_Name,page_C_Id) values('Fixed_Deposit',(select page_C_Id from Page_C where page_C_Name='Deposits'));

insert into Page_D(page_D_Name,page_C_Id) values('Credit_Card',(select page_C_Id from Page_C where page_C_Name='Card_Services'));
insert into Page_D(page_D_Name,page_C_Id) values('Debit_Card',(select page_C_Id from Page_C where page_C_Name='Card_Services'));

insert into Page_D(page_D_Name,page_C_Id) values('Car_Insurance',(select page_C_Id from Page_C where page_C_Name='Insurance'));
insert into Page_D(page_D_Name,page_C_Id) values('House_Insurance',(select page_C_Id from Page_C where page_C_Name='Insurance'));
insert into Page_D(page_D_Name,page_C_Id) values('Life_Insurance',(select page_C_Id from Page_C where page_C_Name='Insurance'));

insert into Page_D(page_D_Name,page_C_Id) values('Accounts',(select page_C_Id from Page_C where page_C_Name='Personal_Banking'));
insert into Page_D(page_D_Name,page_C_Id) values('Loans',(select page_C_Id from Page_C where page_C_Name='Personal_Banking'));
insert into Page_D(page_D_Name,page_C_Id) values('Other_Services',(select page_C_Id from Page_C where page_C_Name='Personal_Banking'));

insert into Page_D(page_D_Name,page_C_Id) values('Home_loans',(select page_C_Id from Page_C where page_C_Name='NRI_Services'));
insert into Page_D(page_D_Name,page_C_Id) values('Trading',(select page_C_Id from Page_C where page_C_Name='NRI_Services'));
insert into Page_D(page_D_Name,page_C_Id) values('Money_transfer&Insurance',(select page_C_Id from Page_C where page_C_Name='NRI_Services'));

insert into Page_D(page_D_Name,page_C_Id) values('Agricultural_Loans',(select page_C_Id from Page_C where page_C_Name='Agricultural_Services'));
insert into Page_D(page_D_Name,page_C_Id) values('Kissan_Credit_Card',(select page_C_Id from Page_C where page_C_Name='Agricultural_Services'));

CREATE TABLE link_Mapping (
  link_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  link_Name varchar(255) NOT NULL,
  parent_link_Id int(10) DEFAULT NULL,
  FOREIGN KEY (parent_link_Id) REFERENCES link_Mapping (link_Id) 
    ON DELETE CASCADE
);

insert into link_Mapping(link_name) values('Investment_Banking');

insert into link_mapping(link_name) values('Retail_Banking');

insert into link_mapping(link_name,parent_link_id) values('Corporate_Finance(Advisory)',1);
insert into link_mapping(link_name,parent_link_id) values('Capital_Market(Execution)',1);
insert into link_mapping(link_name,parent_link_id) values('Private_Banks',2);
insert into link_mapping(link_name,parent_link_id) values('Public_Banks',2);
insert into link_mapping(link_name,parent_link_id) values('Industry Coverage',3);
insert into link_mapping(link_name,parent_link_id) values('Merger&Acquisition',3);
insert into link_mapping(link_name,parent_link_id) values('Equity_ Capital_Market',4);
insert into link_mapping(link_name,parent_link_id) values('Debt_Capital _Market',4);
insert into link_mapping(link_name,parent_link_id) values('Deposits',5);
insert into link_mapping(link_name,parent_link_id) values('Card_Services',5);
insert into link_mapping(link_name,parent_link_id) values('Insurance',5);
insert into link_mapping(link_name,parent_link_id) values('Personal_Banking',6);
insert into link_mapping(link_name,parent_link_id) values('NRI_Services',6);
insert into link_mapping(link_name,parent_link_id) values('Agricultural_Services',6);
insert into link_mapping(link_name,parent_link_id) values('Healthcare',7);
insert into link_mapping(link_name,parent_link_id) values('RealEstate',7);
insert into link_mapping(link_name,parent_link_id) values('Energy',7);
insert into link_mapping(link_name,parent_link_id) values('Joint_Ventures',8);
insert into link_mapping(link_name,parent_link_id) values('Split-Offs',8);
insert into link_mapping(link_name,parent_link_id) values('IPOs',9);
insert into link_mapping(link_name,parent_link_id) values('Follow-On',9);
insert into link_mapping(link_name,parent_link_id) values('Private_Placement',9);
insert into link_mapping(link_name,parent_link_id) values('Investment_Grade',10);
insert into link_mapping(link_name,parent_link_id) values('Leveraged_Finance',10);
insert into link_mapping(link_name,parent_link_id) values('Current_Deposit',11);
insert into link_mapping(link_name,parent_link_id) values('Recurring_Deposit',11);
insert into link_mapping(link_name,parent_link_id) values('Fixed_Deposit',11);
insert into link_mapping(link_name,parent_link_id) values('Credit_Card',12);
insert into link_mapping(link_name,parent_link_id) values('Debit_Card',12);
insert into link_mapping(link_name,parent_link_id) values('Car_Insurance',13);
insert into link_mapping(link_name,parent_link_id) values('House_Insurance',13);
insert into link_mapping(link_name,parent_link_id) values('Life_Insurance',13);
insert into link_mapping(link_name,parent_link_id) values('Accounts',14);
insert into link_mapping(link_name,parent_link_id) values('Loans',14);
insert into link_mapping(link_name,parent_link_id) values('Other_Services',14);
insert into link_mapping(link_name,parent_link_id) values('Home_loans',15);
insert into link_mapping(link_name,parent_link_id) values('Trading',15);
insert into link_mapping(link_name,parent_link_id) values('Money_transfer&Insurance',15);
insert into link_mapping(link_name,parent_link_id) values('Agricultural_Loans',16);
insert into link_mapping(link_name,parent_link_id) values('Kissan_Credit_Card',16);

