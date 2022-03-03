
insert into cozinha (nome) values('Idiana');
insert into cozinha (nome) values('Mexicana');

insert into restaurante (nome, taxa_frete, cozinha_id) values('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Thai Delivery', 15, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Tuk tuk Comida Indiana', 16, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Comida Mineira', 0,2);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Comida Baiana', 0,2);

insert into Forma_pagamento  (id, descricao) values(1, 'Cartão de crédito');
insert into  Forma_pagamento (id, descricao) values(2, 'Cartão de débito');
insert into  Forma_pagamento (id, descricao) values(3, 'Dinheiro');


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2,1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4,3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (5,2);
