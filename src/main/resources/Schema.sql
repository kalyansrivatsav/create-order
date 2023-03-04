create table [order](
  id BIGINT IDENTITY(1,1) primary key,
  productid bigint not null,
  factoryid bigint not null,
  selected_quantity integer,
  CreatedDate datetime,
  status varchar(15),
  foreign key(productid) references product(id),
  foreign key(factoryid) references factory(id)
 )