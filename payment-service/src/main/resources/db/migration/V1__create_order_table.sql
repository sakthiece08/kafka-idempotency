create sequence order_sequence start with 1 increment by 1;


create table orders (
    id  bigint default nextval('order_sequence') not null unique,
    order_id varchar(255) not null,
    product_id varchar(255) not null,
    quantity integer not null,
    price numeric not null,
    created_at timestamp not null default current_timestamp,
    primary key (id)
);