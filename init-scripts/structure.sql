CREATE SEQUENCE public.numero_serie_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    NO CYCLE;

CREATE TABLE public.inventory_item (
    numero_de_serie character varying(5) NOT NULL,
    codigo_item character varying(7),
    data_movimentacao timestamp(6) without time zone NOT NULL,
    descricao character varying(100) NOT NULL,
    disponibilidade character(1) NOT NULL,
    excluido boolean NOT NULL,
    localizacao character varying(250) NOT NULL,
    status character(1) NOT NULL,
    CONSTRAINT inventory_item_pkey PRIMARY KEY (numero_de_serie),
    CONSTRAINT ukmfw90nkw06i418dh1wnn6oij3 UNIQUE (codigo_item)
);

INSERT INTO inventory_item (
    numero_de_serie, 
    codigo_item, 
    data_movimentacao, 
    descricao, 
    disponibilidade, 
    localizacao, 
    status,
    excluido
) 
VALUES 
    -- Materiais de Escritório
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES001', '2024-12-30 12:00:00', 'Papel A4', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES002', '2024-12-30 12:10:00', 'Canetas Esferográficas', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES003', '2024-12-30 12:20:00', 'Marcadores de Quadro Branco', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES004', '2024-12-30 12:30:00', 'Clips para Papel', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES005', '2024-12-30 12:40:00', 'Pasta de Arquivo', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES006', '2024-12-30 12:50:00', 'Calculadora', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES007', '2024-12-30 13:00:00', 'Papel Sulfite', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES008', '2024-12-30 13:10:00', 'Post-its', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES009', '2024-12-30 13:20:00', 'Fitas Adesivas', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ES010', '2024-12-30 13:30:00', 'Grampeador', 'D', 'Estoque - Materiais de Escritório', 'A', false),
    
    -- Equipamentos de Proteção Individual (EPIs)
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'EPI001', '2024-12-30 13:40:00', 'Capacete de Segurança', 'D', 'Estoque - Equipamentos de Proteção Individual', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'EPI002', '2024-12-30 13:50:00', 'Luvas de Proteção', 'D', 'Estoque - Equipamentos de Proteção Individual', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'EPI003', '2024-12-30 14:00:00', 'Óculos de Proteção', 'D', 'Estoque - Equipamentos de Proteção Individual', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'EPI004', '2024-12-30 14:10:00', 'Protetor Auricular', 'D', 'Estoque - Equipamentos de Proteção Individual', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'EPI005', '2024-12-30 14:20:00', 'Máscara Respiratória', 'D', 'Estoque - Equipamentos de Proteção Individual', 'A', false),
    
    -- Materiais de Limpeza
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ML001', '2024-12-30 14:30:00', 'Detergente Líquido', 'D', 'Estoque - Materiais de Limpeza', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ML002', '2024-12-30 14:40:00', 'Desinfetante', 'D', 'Estoque - Materiais de Limpeza', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ML003', '2024-12-30 14:50:00', 'Álcool em Gel', 'D', 'Estoque - Materiais de Limpeza', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ML004', '2024-12-30 15:00:00', 'Sabão em Barra', 'D', 'Estoque - Materiais de Limpeza', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'ML005', '2024-12-30 15:10:00', 'Desengordurante', 'D', 'Estoque - Materiais de Limpeza', 'A', false),
    
    -- Ferramentas
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'FERR001', '2024-12-30 15:20:00', 'Chave Inglesa', 'D', 'Estoque - Ferramentas', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'FERR002', '2024-12-30 15:30:00', 'Alicate de Corte', 'D', 'Estoque - Ferramentas', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'FERR003', '2024-12-30 15:40:00', 'Martelo', 'D', 'Estoque - Ferramentas', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'FERR004', '2024-12-30 15:50:00', 'Chave de Fenda', 'D', 'Estoque - Ferramentas', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'FERR005', '2024-12-30 16:00:00', 'Serrote', 'D', 'Estoque - Ferramentas', 'A', false),
    
    -- Componentes
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'COMP001', '2024-12-30 16:10:00', 'Resistor 220Ω', 'D', 'Estoque - Componentes', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'COMP002', '2024-12-30 16:20:00', 'Capacitor 10µF', 'D', 'Estoque - Componentes', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'COMP003', '2024-12-30 16:30:00', 'Transistor NPN', 'D', 'Estoque - Componentes', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'COMP004', '2024-12-30 16:40:00', 'LED Vermelho', 'D', 'Estoque - Componentes', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'COMP005', '2024-12-30 16:50:00', 'Diodo Retificador', 'D', 'Estoque - Componentes', 'A', false),
    
    -- Peças
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'PECA001', '2024-12-30 17:00:00', 'Motor Elétrico', 'D', 'Estoque - Peças', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'PECA002', '2024-12-30 17:10:00', 'Bateria Recarregável', 'D', 'Estoque - Peças', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'PECA003', '2024-12-30 17:20:00', 'Cabo USB', 'D', 'Estoque - Peças', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'PECA004', '2024-12-30 17:30:00', 'Carregador', 'D', 'Estoque - Peças', 'A', false),
    (LPAD(nextval('numero_serie_seq')::text, 5, '0'), 'PECA005', '2024-12-30 17:40:00', 'Fonte de Alimentação', 'D', 'Estoque - Peças', 'A', false);

