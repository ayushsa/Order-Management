insert into orders (id,order_refrence_no,order_status,order_total,payment_currency,payment_mode,shipping_adderess,user_id,created_at, updated_at)
values(1,'ORD001','Preparing','6757.00','INR','CC','Shipping Adderess','u_ajeet',sysdate(), sysdate());


insert into order_item (id, description,ordered_price,ordered_qty , product_name  ,product_status ,  product_id ,order_id,created_at, updated_at)
values(1,  'p_description', '30.80', '2','Product 1','','123', 1 ,sysdate(), sysdate());

insert into order_item (id, description,ordered_price,ordered_qty , product_name  ,product_status ,  product_id ,order_id,created_at, updated_at)
values(2,  'p_description', '30.80', '2','Product 2','','124', 1 ,sysdate(), sysdate() );