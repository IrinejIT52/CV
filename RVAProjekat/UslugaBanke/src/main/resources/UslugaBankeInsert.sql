
	insert into banka values(nextval('banka_seq'),100228212,'(381)11 222-600','Adiko Banka');
	insert into banka values(nextval('banka_seq'),100228221,'(381)11/2205-500','Alta Banka');
	insert into banka values(nextval('banka_seq'),100228332,'(381)11/3952-213','Api Banka');
	insert into banka values(nextval('banka_seq'),100228352,'(381)11/2011-200','Banka Intesa');
	insert into banka values(nextval('banka_seq'),100228432,'0800/201-201','ERSTE Bank');
	
	insert into korisnik values(nextval('korisnik_seq'),'Irinej','1705001153150','Kuzman');
	insert into korisnik values(nextval('korisnik_seq'),'Sergej','1302997130123','Kuzman');
	insert into korisnik values(nextval('korisnik_seq'),'Jovan','3101001153150','Tamindzija');
	insert into korisnik values(nextval('korisnik_seq'),'Anastasija','180100115310','Kovacevic');
	insert into korisnik values(nextval('korisnik_seq'),'Natasa','0606004154152','Radic');
	
	insert into filijala values(nextval('filijala_seq'),'Novi Sad,Cara Dusana 3',2,true,5);
	insert into filijala values(nextval('filijala_seq'),'Novi Sad,Nikole Tesle 12',4,true,4);
	insert into filijala values(nextval('filijala_seq'),'Novi Sad,Bulevar Oslobodjenja 122',3,true,3);
	insert into filijala values(nextval('filijala_seq'),'Novi Sad,Futoski put 32',1,false,2);
	insert into filijala values(nextval('filijala_seq'),'Novi Sad,Danila Kisa 4',2,false,1);
	
	insert into usluga values(nextval('usluga_seq'),'2023-01-08','Isplata','Isplata novca sa racuna',60,1,5);
	insert into usluga values(nextval('usluga_seq'),'2023-02-13','Isplata','Isplata novca sa racuna',120,2,4);
	insert into usluga values(nextval('usluga_seq'),'2023-02-14','Uplata','Uplata novca na racun',60,3,3);
	insert into usluga values(nextval('usluga_seq'),'2023-02-21','Uplata','Placanje rezija',30,4,2);
	insert into usluga values(nextval('usluga_seq'),'2023-03-10','Uplata','Placanje rezija',30,5,1);
								