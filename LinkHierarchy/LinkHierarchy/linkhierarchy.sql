
create database LinkTablesHierarchy;
use LinkTablesHierarchy;

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
