

INSERT INTO  users(id,enabled,password,user_name) VALUES(1,true,'$2a$10$sr2ZuTYlZ.LsliI6sv.HqeH39WsA.gz9Fdr.S0CyCBByyPQ8TObvS','hampcode');
INSERT INTO  users(id,enabled,password,user_name) VALUES(2,true,'$2a$10$ZoqMrCUHNN8lE2g1zssyuucNhExlYwN06kM/Jo6vEBhyTQduQ7iCy','usercode');


INSERT INTO authorities(id,authority,user_id) VALUES(1,'ROLE_ADMIN',1);
INSERT INTO authorities(id,authority,user_id) VALUES(2,'ROLE_USER',2);