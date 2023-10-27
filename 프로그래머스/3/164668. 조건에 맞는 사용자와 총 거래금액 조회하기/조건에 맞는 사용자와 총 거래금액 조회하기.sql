select b.USER_ID, b.NICKNAME, sum(price) as TOTAL_SALES
from used_goods_board as a
left join used_goods_user as b
on a.writer_id = b.user_id
where status = 'DONE'
group by writer_id
having TOTAL_SALES >= 700000
order by TOTAL_SALES;


