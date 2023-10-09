-- public.pessoa definition

-- Drop table

-- DROP TABLE public.pessoa;

CREATE TABLE public.pessoa (
	id varchar(255) NOT NULL,
	apelido varchar(32) NOT NULL,
	nascimento timestamp(6) NOT NULL,
	nome varchar(100) NOT NULL,
	searchable varchar(255) NULL,
	stack _varchar NULL,
	CONSTRAINT pessoa_pkey PRIMARY KEY (id),
	CONSTRAINT ukmupg31uvc3g3c6gw4ko94oby9 UNIQUE (apelido)
);
CREATE INDEX pessoa_apelido_indice ON public.pessoa (apelido text_ops);
CREATE INDEX pessoa_id_indice ON public.pessoa (id text_ops);
CREATE INDEX pessoa_searchable_indice ON public.pessoa (searchable text_ops);