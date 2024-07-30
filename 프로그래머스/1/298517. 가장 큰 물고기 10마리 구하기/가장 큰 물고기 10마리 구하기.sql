select id, length
from fish_info
where length >= 10
order by 2 desc, 1 asc
limit 10;