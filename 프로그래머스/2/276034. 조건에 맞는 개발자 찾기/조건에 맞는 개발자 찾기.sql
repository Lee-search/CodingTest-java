select id, email, first_name, last_name
from developers
-- & 비교해서 포함되면 참, 아니면 거짓
where skill_code & (select code from skillcodes where name = 'C#')
or skill_code & (select code from skillcodes where name= 'Python')
order by id;