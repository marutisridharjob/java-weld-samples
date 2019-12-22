#h2 in memory database test setup
INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME, MODIFIED_AT, MODIFIED_BY) VALUES (5, ' basic ', 'test ', TIMESTAMP '2019-12-22 15:02:09.567', 'testname');  
INSERT INTO ACCOUNT(EMAIL, ACCNAME, MODIFIED_AT, MODIFIED_BY, PERSON_ID) VALUES ('test2@test.net', 'testname', TIMESTAMP '2019-12-22 15:02:09.588', 'testname', 5);        
commit;