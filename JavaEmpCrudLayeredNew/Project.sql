select * from app.Emp;

create sequence app.seq_emp_empNo1 as int start with 1000 ;

drop sequence app.emp_empNo;

drop sequence app.seq_emp_empNo;
select app.seq_emp_empNo.nextval from dual;

INSERT INTO app.emp (empNo, ename, sal) VALUES(next value for app.seq_emp_empNo1, ?, ?);

delete from app.Emp where empNo = 1000;