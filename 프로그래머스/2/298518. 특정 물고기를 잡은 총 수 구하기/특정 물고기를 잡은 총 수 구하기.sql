select count(*) as fish_count
from fish_info as fi
join fish_name_info as fni
on fi.fish_type = fni.fish_type
where fish_name = 'BASS' or fish_name = 'SNAPPER';