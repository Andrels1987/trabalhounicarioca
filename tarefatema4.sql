describe professor;
select * from disciplina;
select * from professor;
select * from aluno;
select * from aluno_disciplina;
use escola;

insert into professor values (null, 'Manoel','Dr'), (null,'Tavares', 'Dr');
insert into disciplina values (null, 'Algoritimos',60), (null,'Redes', 60);
insert into aluno values (null, 'Paulo','CC', 1), (null,'Manoel','Redes', 1), (null,'Pedro','CC', 1), (null,'Maria','Redes', 2);
insert into aluno_disciplina values (null, 1, 1), 
						(null,1, 2), 
						(null,2,2), 
						(null,3,1);


select a.nome as aluno,p.nome as professor, d.nome as disciplina
from aluno a join aluno_disciplina ad  on a.id = ad.idAluno
join professor p on a.idProfessor = p.id
join disciplina d on ad.idDisciplina = d.id order by ad.idDisciplina asc, a.id;













