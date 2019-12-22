mysql> create database location;

mysql> create user 'loc'@'localhost' identified by 'password';

mysql> grant all on location.* to 'loc'@'localhost';


select t1.Name as cityname, t12.Name as distname, t1.code, t12.code from
geo t1 inner join geo_assoc t2 on t1.code = t2.code_to 
inner join geo t12 on t2.code = t12.code 
where t1.name LIKE 'Kharar' AND t1.type=2



SELECT t1.name as statename, t12.name as distname, t12.code as distcode from geo t1 inner join geo_assoc t2 on t1.code = t2.code inner join geo t12 on t2.code_to = t12.code AND t2.relation_type=0 where t1.name LIKE 'Mohali' AND t1.type=1
