--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cabecera_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cabecera_compra (
    id_cabecera_compra integer NOT NULL,
    cabcomp_vendedor integer NOT NULL,
    cabcomp_proveedor integer NOT NULL
);


ALTER TABLE public.cabecera_compra OWNER TO postgres;

--
-- Name: cabecera_compra_id_cabecera_compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cabecera_compra_id_cabecera_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cabecera_compra_id_cabecera_compra_seq OWNER TO postgres;

--
-- Name: cabecera_compra_id_cabecera_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cabecera_compra_id_cabecera_compra_seq OWNED BY public.cabecera_compra.id_cabecera_compra;


--
-- Name: cabecera_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cabecera_factura (
    id_cabecera_factura integer NOT NULL,
    cab_cliente integer NOT NULL,
    cab_empresa integer NOT NULL,
    cab_vendedor integer NOT NULL
);


ALTER TABLE public.cabecera_factura OWNER TO postgres;

--
-- Name: cabecera_factura_id_cabecera_factura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cabecera_factura_id_cabecera_factura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cabecera_factura_id_cabecera_factura_seq OWNER TO postgres;

--
-- Name: cabecera_factura_id_cabecera_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cabecera_factura_id_cabecera_factura_seq OWNED BY public.cabecera_factura.id_cabecera_factura;


--
-- Name: cabecera_ingreso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cabecera_ingreso (
    id_cabecera_ingreso integer NOT NULL,
    cabing_proveedor integer NOT NULL,
    cabing_vendedor integer NOT NULL,
    cabing_fecha_ingreso date NOT NULL,
    cabing_autorizacion character varying(15) NOT NULL,
    cabing_numero_factura character varying(15) NOT NULL,
    cabing_fecha_emision date NOT NULL,
    cabing_fecha_caducacion date NOT NULL,
    cabing_empresa integer NOT NULL
);


ALTER TABLE public.cabecera_ingreso OWNER TO postgres;

--
-- Name: cabecera_ingreso_id_cabecera_ingreso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cabecera_ingreso_id_cabecera_ingreso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cabecera_ingreso_id_cabecera_ingreso_seq OWNER TO postgres;

--
-- Name: cabecera_ingreso_id_cabecera_ingreso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cabecera_ingreso_id_cabecera_ingreso_seq OWNED BY public.cabecera_ingreso.id_cabecera_ingreso;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    cli_usuario character varying(25) NOT NULL,
    cli_telefono character varying(15) NOT NULL,
    cli_email character varying(60) NOT NULL,
    cli_direccion character varying(100) NOT NULL,
    cli_ruc_cedula character varying(13) NOT NULL,
    cli_fecha_registro date NOT NULL,
    cli_codigo character varying(10) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- Name: compra_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compra_producto (
    id_compra_producto integer NOT NULL,
    comprod_cabecera_compra integer NOT NULL,
    comprod_aprobado boolean,
    comprod_fecha date NOT NULL,
    comprod_subtotal numeric(8,2),
    comprod_iva numeric(8,2),
    comprod_total numeric(8,2)
);


ALTER TABLE public.compra_producto OWNER TO postgres;

--
-- Name: compra_producto_id_compra_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_producto_id_compra_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.compra_producto_id_compra_producto_seq OWNER TO postgres;

--
-- Name: compra_producto_id_compra_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_producto_id_compra_producto_seq OWNED BY public.compra_producto.id_compra_producto;


--
-- Name: detalle_abono; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_abono (
    id_detalle_abono integer NOT NULL,
    detab_estado_pedido integer NOT NULL,
    detab_abono numeric(8,2) NOT NULL,
    detab_saldo_anterior numeric(8,2) NOT NULL,
    detab_saldo_actual numeric(8,2) NOT NULL,
    detab_fecha_abono date NOT NULL,
    detab_cliente integer NOT NULL,
    detab_vendedor integer NOT NULL
);


ALTER TABLE public.detalle_abono OWNER TO postgres;

--
-- Name: detalle_abono_id_detalle_abono_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_abono_id_detalle_abono_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.detalle_abono_id_detalle_abono_seq OWNER TO postgres;

--
-- Name: detalle_abono_id_detalle_abono_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_abono_id_detalle_abono_seq OWNED BY public.detalle_abono.id_detalle_abono;


--
-- Name: detalle_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_compra (
    id_detalle_compra integer NOT NULL,
    detcomp_compra_producto integer NOT NULL,
    detcomp_codigo_producto character varying(15),
    detcomp_nombre_producto character varying(60) NOT NULL,
    detcomp_descripcion character varying(100),
    detcomp_cantidad integer NOT NULL,
    detcomp_precio_unit numeric(7,2) NOT NULL,
    detcomp_precio_total numeric(7,2) NOT NULL
);


ALTER TABLE public.detalle_compra OWNER TO postgres;

--
-- Name: detalle_compra_id_detalle_compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_compra_id_detalle_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.detalle_compra_id_detalle_compra_seq OWNER TO postgres;

--
-- Name: detalle_compra_id_detalle_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_compra_id_detalle_compra_seq OWNED BY public.detalle_compra.id_detalle_compra;


--
-- Name: detalle_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_factura (
    id_detalle_factura integer NOT NULL,
    det_cantidad integer NOT NULL,
    det_producto integer NOT NULL,
    det_factura integer NOT NULL,
    det_precio_unitario numeric(8,2) NOT NULL,
    det_iva numeric(8,2) NOT NULL,
    det_subtotal numeric(8,2),
    det_precio_total numeric(8,2) NOT NULL
);


ALTER TABLE public.detalle_factura OWNER TO postgres;

--
-- Name: detalle_factura_id_detalle_factura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_factura_id_detalle_factura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.detalle_factura_id_detalle_factura_seq OWNER TO postgres;

--
-- Name: detalle_factura_id_detalle_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_factura_id_detalle_factura_seq OWNED BY public.detalle_factura.id_detalle_factura;


--
-- Name: detalle_ingreso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_ingreso (
    id_detalle_ingreso integer NOT NULL,
    deting_factura_ingreso integer NOT NULL,
    deting_producto integer NOT NULL,
    deting_cantidad integer NOT NULL,
    deting_precio_unitario numeric(8,2) NOT NULL,
    deting_iva numeric(8,2) NOT NULL,
    deting_subtotal numeric(8,2),
    deting_precio_total numeric(8,2) NOT NULL
);


ALTER TABLE public.detalle_ingreso OWNER TO postgres;

--
-- Name: detalle_ingreso_id_detalle_ingreso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_ingreso_id_detalle_ingreso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.detalle_ingreso_id_detalle_ingreso_seq OWNER TO postgres;

--
-- Name: detalle_ingreso_id_detalle_ingreso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_ingreso_id_detalle_ingreso_seq OWNED BY public.detalle_ingreso.id_detalle_ingreso;


--
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresa (
    id_empresa integer NOT NULL,
    emp_ruc character varying(13) NOT NULL,
    emp_nombre_empresa character varying(100) NOT NULL,
    emp_gerente integer NOT NULL,
    emp_matriz character varying(100) NOT NULL,
    emp_sucursal character varying(100),
    emp_pais character varying(50) NOT NULL,
    emp_provincia character varying(50) NOT NULL,
    emp_ciudad character varying(50) NOT NULL,
    emp_telefono character varying(15) NOT NULL,
    emp_email character varying(60) NOT NULL,
    emp_fecha_inicio date NOT NULL
);


ALTER TABLE public.empresa OWNER TO postgres;

--
-- Name: empresa_id_empresa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empresa_id_empresa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresa_id_empresa_seq OWNER TO postgres;

--
-- Name: empresa_id_empresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empresa_id_empresa_seq OWNED BY public.empresa.id_empresa;


--
-- Name: estado_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_pedido (
    id_estado_pedido integer NOT NULL,
    est_factura integer NOT NULL,
    est_valor_total numeric(8,2) NOT NULL,
    est_saldo numeric(8,2) NOT NULL,
    est_fecha_emision date NOT NULL
);


ALTER TABLE public.estado_pedido OWNER TO postgres;

--
-- Name: estado_pedido_id_estado_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_pedido_id_estado_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_pedido_id_estado_pedido_seq OWNER TO postgres;

--
-- Name: estado_pedido_id_estado_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_pedido_id_estado_pedido_seq OWNED BY public.estado_pedido.id_estado_pedido;


--
-- Name: factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura (
    id_factura integer NOT NULL,
    fact_numero_factura character varying(20) NOT NULL,
    fact_fecha_emision date NOT NULL,
    fact_fecha_remision date NOT NULL,
    fact_fecha_autorizacion date NOT NULL,
    fact_forma_pago integer NOT NULL,
    fact_tipo_factura integer NOT NULL,
    fact_cabecera_factura integer NOT NULL,
    fact_estado boolean NOT NULL,
    fact_entregado boolean NOT NULL,
    fact_descuento numeric(4,2),
    fact_subtotal numeric(8,2),
    fact_iva numeric(8,2),
    fact_total numeric(8,2)
);


ALTER TABLE public.factura OWNER TO postgres;

--
-- Name: factura_id_factura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.factura_id_factura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.factura_id_factura_seq OWNER TO postgres;

--
-- Name: factura_id_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.factura_id_factura_seq OWNED BY public.factura.id_factura;


--
-- Name: factura_ingreso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_ingreso (
    id_factura_ingreso integer NOT NULL,
    facting_cabecera_ingreso integer NOT NULL,
    facting_subtotal numeric(8,2),
    facting_iva numeric(8,2),
    facting_total numeric(8,2)
);


ALTER TABLE public.factura_ingreso OWNER TO postgres;

--
-- Name: factura_ingreso_id_factura_ingreso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.factura_ingreso_id_factura_ingreso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.factura_ingreso_id_factura_ingreso_seq OWNER TO postgres;

--
-- Name: factura_ingreso_id_factura_ingreso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.factura_ingreso_id_factura_ingreso_seq OWNED BY public.factura_ingreso.id_factura_ingreso;


--
-- Name: forma_pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forma_pago (
    id_forma_pago integer NOT NULL,
    fp_nombre character varying(50) NOT NULL
);


ALTER TABLE public.forma_pago OWNER TO postgres;

--
-- Name: forma_pago_id_forma_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.forma_pago_id_forma_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forma_pago_id_forma_pago_seq OWNER TO postgres;

--
-- Name: forma_pago_id_forma_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.forma_pago_id_forma_pago_seq OWNED BY public.forma_pago.id_forma_pago;


--
-- Name: gerente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gerente (
    id_gerente integer NOT NULL,
    ger_usuario character varying(25) NOT NULL,
    ger_telefono character varying(15) NOT NULL,
    ger_email character varying(60) NOT NULL,
    ger_direccion character varying(100) NOT NULL,
    ger_cedula character varying(10) NOT NULL
);


ALTER TABLE public.gerente OWNER TO postgres;

--
-- Name: gerente_id_gerente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.gerente_id_gerente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.gerente_id_gerente_seq OWNER TO postgres;

--
-- Name: gerente_id_gerente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.gerente_id_gerente_seq OWNED BY public.gerente.id_gerente;


--
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    id_producto integer NOT NULL,
    prod_codigo character varying(15) NOT NULL,
    prod_cantidad integer NOT NULL,
    prod_nombre character varying(60) NOT NULL,
    prod_descripcion character varying(100) NOT NULL,
    prod_proveedor integer NOT NULL,
    prod_tipo_producto integer NOT NULL,
    prod_pvproveedor numeric(7,2) NOT NULL,
    prod_iva numeric(4,2) NOT NULL,
    prod_pvpublico numeric(7,2) NOT NULL,
    prod_imagen character varying(100),
    prod_talla_producto integer NOT NULL
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- Name: producto_id_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.producto_id_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.producto_id_producto_seq OWNER TO postgres;

--
-- Name: producto_id_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.producto_id_producto_seq OWNED BY public.producto.id_producto;


--
-- Name: talla_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.talla_producto (
    id_talla_producto integer NOT NULL,
    talla_nombre character varying(15) NOT NULL
);


ALTER TABLE public.talla_producto OWNER TO postgres;

--
-- Name: talla_producto_id_talla_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.talla_producto_id_talla_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.talla_producto_id_talla_producto_seq OWNER TO postgres;

--
-- Name: talla_producto_id_talla_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.talla_producto_id_talla_producto_seq OWNED BY public.talla_producto.id_talla_producto;


--
-- Name: tipo_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_factura (
    id_tipo_factura integer NOT NULL,
    tf_nombre character varying(50) NOT NULL
);


ALTER TABLE public.tipo_factura OWNER TO postgres;

--
-- Name: tipo_factura_id_tipo_factura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_factura_id_tipo_factura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_factura_id_tipo_factura_seq OWNER TO postgres;

--
-- Name: tipo_factura_id_tipo_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_factura_id_tipo_factura_seq OWNED BY public.tipo_factura.id_tipo_factura;


--
-- Name: tipo_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_producto (
    id_tipo_producto integer NOT NULL,
    tp_nombre character varying(50) NOT NULL
);


ALTER TABLE public.tipo_producto OWNER TO postgres;

--
-- Name: tipo_producto_id_tipo_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_producto_id_tipo_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_producto_id_tipo_producto_seq OWNER TO postgres;

--
-- Name: tipo_producto_id_tipo_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_producto_id_tipo_producto_seq OWNED BY public.tipo_producto.id_tipo_producto;


--
-- Name: tipo_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_usuario (
    id_tipo_usuario integer NOT NULL,
    tipus_nombre character varying(25) NOT NULL
);


ALTER TABLE public.tipo_usuario OWNER TO postgres;

--
-- Name: tipo_usuario_id_tipo_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_usuario_id_tipo_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_usuario_id_tipo_usuario_seq OWNER TO postgres;

--
-- Name: tipo_usuario_id_tipo_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_usuario_id_tipo_usuario_seq OWNED BY public.tipo_usuario.id_tipo_usuario;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario character varying(25) NOT NULL,
    us_email character varying(60) NOT NULL,
    us_password character varying(100) NOT NULL,
    us_activo boolean NOT NULL,
    us_nombres character varying(60) NOT NULL,
    us_apellidos character varying(60) NOT NULL,
    us_fecha_registro date NOT NULL,
    us_tipousuario integer NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: vendedor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vendedor (
    id_vendedor integer NOT NULL,
    ven_usuario character varying(25) NOT NULL,
    ven_telefono character varying(15) NOT NULL,
    ven_email character varying(60) NOT NULL,
    ven_direccion character varying(100) NOT NULL,
    ven_cedula character varying(10) NOT NULL,
    ven_sueldo numeric(7,2) NOT NULL,
    ven_fecha_inicio date NOT NULL
);


ALTER TABLE public.vendedor OWNER TO postgres;

--
-- Name: vendedor_id_vendedor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vendedor_id_vendedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vendedor_id_vendedor_seq OWNER TO postgres;

--
-- Name: vendedor_id_vendedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vendedor_id_vendedor_seq OWNED BY public.vendedor.id_vendedor;


--
-- Name: cabecera_compra id_cabecera_compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_compra ALTER COLUMN id_cabecera_compra SET DEFAULT nextval('public.cabecera_compra_id_cabecera_compra_seq'::regclass);


--
-- Name: cabecera_factura id_cabecera_factura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_factura ALTER COLUMN id_cabecera_factura SET DEFAULT nextval('public.cabecera_factura_id_cabecera_factura_seq'::regclass);


--
-- Name: cabecera_ingreso id_cabecera_ingreso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_ingreso ALTER COLUMN id_cabecera_ingreso SET DEFAULT nextval('public.cabecera_ingreso_id_cabecera_ingreso_seq'::regclass);


--
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: compra_producto id_compra_producto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra_producto ALTER COLUMN id_compra_producto SET DEFAULT nextval('public.compra_producto_id_compra_producto_seq'::regclass);


--
-- Name: detalle_abono id_detalle_abono; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_abono ALTER COLUMN id_detalle_abono SET DEFAULT nextval('public.detalle_abono_id_detalle_abono_seq'::regclass);


--
-- Name: detalle_compra id_detalle_compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compra ALTER COLUMN id_detalle_compra SET DEFAULT nextval('public.detalle_compra_id_detalle_compra_seq'::regclass);


--
-- Name: detalle_factura id_detalle_factura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura ALTER COLUMN id_detalle_factura SET DEFAULT nextval('public.detalle_factura_id_detalle_factura_seq'::regclass);


--
-- Name: detalle_ingreso id_detalle_ingreso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ingreso ALTER COLUMN id_detalle_ingreso SET DEFAULT nextval('public.detalle_ingreso_id_detalle_ingreso_seq'::regclass);


--
-- Name: empresa id_empresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa ALTER COLUMN id_empresa SET DEFAULT nextval('public.empresa_id_empresa_seq'::regclass);


--
-- Name: estado_pedido id_estado_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_pedido ALTER COLUMN id_estado_pedido SET DEFAULT nextval('public.estado_pedido_id_estado_pedido_seq'::regclass);


--
-- Name: factura id_factura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura ALTER COLUMN id_factura SET DEFAULT nextval('public.factura_id_factura_seq'::regclass);


--
-- Name: factura_ingreso id_factura_ingreso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_ingreso ALTER COLUMN id_factura_ingreso SET DEFAULT nextval('public.factura_ingreso_id_factura_ingreso_seq'::regclass);


--
-- Name: forma_pago id_forma_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_pago ALTER COLUMN id_forma_pago SET DEFAULT nextval('public.forma_pago_id_forma_pago_seq'::regclass);


--
-- Name: gerente id_gerente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerente ALTER COLUMN id_gerente SET DEFAULT nextval('public.gerente_id_gerente_seq'::regclass);


--
-- Name: producto id_producto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto ALTER COLUMN id_producto SET DEFAULT nextval('public.producto_id_producto_seq'::regclass);


--
-- Name: talla_producto id_talla_producto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talla_producto ALTER COLUMN id_talla_producto SET DEFAULT nextval('public.talla_producto_id_talla_producto_seq'::regclass);


--
-- Name: tipo_factura id_tipo_factura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_factura ALTER COLUMN id_tipo_factura SET DEFAULT nextval('public.tipo_factura_id_tipo_factura_seq'::regclass);


--
-- Name: tipo_producto id_tipo_producto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_producto ALTER COLUMN id_tipo_producto SET DEFAULT nextval('public.tipo_producto_id_tipo_producto_seq'::regclass);


--
-- Name: tipo_usuario id_tipo_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_usuario ALTER COLUMN id_tipo_usuario SET DEFAULT nextval('public.tipo_usuario_id_tipo_usuario_seq'::regclass);


--
-- Name: vendedor id_vendedor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendedor ALTER COLUMN id_vendedor SET DEFAULT nextval('public.vendedor_id_vendedor_seq'::regclass);


--
-- Data for Name: cabecera_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cabecera_compra (id_cabecera_compra, cabcomp_vendedor, cabcomp_proveedor) FROM stdin;
1	2	1
6	2	1
9	2	4
10	2	2
12	2	2
13	2	2
14	2	3
15	1	3
\.


--
-- Data for Name: cabecera_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cabecera_factura (id_cabecera_factura, cab_cliente, cab_empresa, cab_vendedor) FROM stdin;
1	1	1	2
2	1	1	2
3	2	1	2
4	1	1	2
5	1	1	2
6	1	1	2
7	3	1	2
8	5	1	1
9	5	1	1
10	6	1	1
11	5	1	2
12	5	1	2
13	5	1	2
14	1	1	2
15	1	1	2
17	5	1	2
18	1	1	2
19	5	1	3
20	6	1	2
21	6	1	2
22	7	1	2
23	7	1	2
24	8	1	2
\.


--
-- Data for Name: cabecera_ingreso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cabecera_ingreso (id_cabecera_ingreso, cabing_proveedor, cabing_vendedor, cabing_fecha_ingreso, cabing_autorizacion, cabing_numero_factura, cabing_fecha_emision, cabing_fecha_caducacion, cabing_empresa) FROM stdin;
1	3	2	2022-02-20	002	001	2022-02-01	2022-02-01	1
2	3	2	2022-02-21	02	002	2022-02-01	2022-02-01	1
3	4	2	2022-02-21	02	002	2022-02-01	2022-02-01	1
4	2	2	2022-02-21	002	05	2022-02-01	2022-02-01	1
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id_cliente, cli_usuario, cli_telefono, cli_email, cli_direccion, cli_ruc_cedula, cli_fecha_registro, cli_codigo) FROM stdin;
1	CLI-1003938410	0989061898	mario@utn.edu.ec	El Tejar	1003938410	2022-02-20	CLI-1
2	CLI-1003727060	0990449305	inessalazar@hotmail.com	Manzano Guarangui	1003727060	2022-02-20	CLI-2
3	CLI-1001590650	0991566452	miguel@gmail.com	Ibarra	1001590650	2022-02-20	CLI-3
5	CLI-1003938477	0989061898	elenita_cli@gmail.com	San Francisco del Tejar	1003938477	2022-02-21	CLI-4
6	CLI-1003639584	0989061251	lizbeth_cli@hotmail.com	Cayambe - Cajas	1003639584	2022-02-21	CLI-5
7	CLI-0203656987	0256256894	roblespedro@hotmail.com	La Esperanza - Ibarra	0203656987	2022-02-24	CLI-6
8	CLI-9999999999	***************	**********************	*******************	9999999999	2022-02-24	CLI-7
\.


--
-- Data for Name: compra_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.compra_producto (id_compra_producto, comprod_cabecera_compra, comprod_aprobado, comprod_fecha, comprod_subtotal, comprod_iva, comprod_total) FROM stdin;
1	1	f	2022-02-20	219.46	26.34	245.80
6	12	t	2022-02-21	37.37	4.48	41.85
2	6	f	2022-02-21	847.63	101.72	949.35
3	9	f	2022-02-21	95.38	11.45	106.83
4	10	t	2022-02-21	139.29	16.71	156.00
9	15	f	2022-02-22	6033.93	724.07	6758.00
8	14	t	2022-02-22	9758.93	1171.10	10930.00
7	13	f	2022-02-21	13.70	1.64	15.34
\.


--
-- Data for Name: detalle_abono; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_abono (id_detalle_abono, detab_estado_pedido, detab_abono, detab_saldo_anterior, detab_saldo_actual, detab_fecha_abono, detab_cliente, detab_vendedor) FROM stdin;
1	1	121.25	1227.29	1106.00	2022-02-20	1	2
2	1	150.00	1106.00	956.00	2022-02-20	1	2
3	1	50.00	956.00	906.00	2022-02-20	1	2
4	1	70.00	906.00	836.00	2022-02-20	1	2
5	1	215.25	836.00	620.75	2022-02-20	1	2
6	1	15.25	620.75	605.50	2022-02-20	1	2
7	1	152.25	605.50	453.25	2022-02-20	1	2
8	2	10.00	118.44	108.44	2022-02-20	1	2
9	2	15.00	108.44	93.44	2022-02-20	1	2
10	3	11.00	183.20	172.20	2022-02-20	3	2
11	1	168.00	453.25	285.25	2022-02-21	1	2
12	4	36.14	826.95	790.81	2022-02-21	5	1
13	5	140.00	736.26	596.26	2022-02-21	6	1
14	1	50.00	285.25	235.25	2022-02-21	1	2
15	5	13.00	596.26	583.26	2022-02-21	6	2
16	6	15.00	123.42	108.42	2022-02-21	5	2
17	7	15.00	60.09	45.09	2022-02-21	5	2
18	4	124.00	790.81	666.81	2022-02-22	5	2
19	4	200.00	666.81	466.81	2022-02-22	5	2
20	4	50.00	466.81	416.81	2022-02-22	5	2
21	4	50.00	416.81	366.81	2022-02-22	5	2
22	4	300.00	366.81	66.81	2022-02-22	5	2
23	4	66.81	66.81	0.00	2022-02-22	5	2
24	3	12.00	172.20	160.20	2022-02-24	3	2
25	5	17.00	583.26	566.26	2022-02-24	6	2
26	1	98.25	235.25	137.00	2022-02-24	1	2
27	2	72.00	93.44	21.44	2022-02-24	1	2
28	8	15.23	1191.50	1176.30	2022-02-24	7	2
29	8	326.00	1176.30	850.30	2022-02-24	7	2
\.


--
-- Data for Name: detalle_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_compra (id_detalle_compra, detcomp_compra_producto, detcomp_codigo_producto, detcomp_nombre_producto, detcomp_descripcion, detcomp_cantidad, detcomp_precio_unit, detcomp_precio_total) FROM stdin;
1	1	RI-2149	Calzoncillos sueltos - Boxers	Calzoncillos sueltos - Boxers	4	12.14	48.56
2	1	RI-2175	Slippers - Pantuflas	Slippers - Pantuflas	4	9.01	36.04
3	1	RIn-2012	Botas\tBoots	Botas\tBoots	4	13.76	55.04
4	1	RIn-2481	Abrigo\tCoat	Abrigo\tCoat	4	13.27	53.08
5	1	RIn-2837	Cárdigan\tCardigan	Cárdigan\tCardigan	4	13.27	53.08
6	2	RI-2089	Sostén - sujetador\tBrassier	Sostén - sujetador\tBrassier	21	12.47	261.87
7	2	RIn-2887	Camiseta de manga larga\tLong-sleeve	Camiseta de manga larga\tLong-sleeve	51	13.48	687.48
8	3	RI-2089	Sostén - sujetador\tBrassier	Sostén - sujetador\tBrassier	1	12.47	12.47
9	3	RIn-2887	Camiseta de manga larga\tLong-sleeve	Camiseta de manga larga\tLong-sleeve	7	13.48	94.36
10	4	RI-2089	Sostén - sujetador\tBrassier	Sostén - sujetador\tBrassier	5	12.47	62.35
11	4	RIn-2887	Camiseta de manga larga\tLong-sleeve	Camiseta de manga larga\tLong-sleeve	5	13.48	67.40
12	4	Rn-222	Chompa pequeña	Chompa pequeña gris	11	15.25	26.25
13	6	RIn-2432	Casaca / chaqueta de cuero\tLeather jacket	Casaca / chaqueta de cuero\tLeather jacket	1	13.95	13.95
14	6	RIn-2432	Casaca / chaqueta de cuero\tLeather jacket	Casaca / chaqueta de cuero\tLeather jacket	1	13.95	13.95
15	6	RIn-2432	Casaca / chaqueta de cuero\tLeather jacket	Casaca / chaqueta de cuero\tLeather jacket	1	13.95	13.95
16	7	RIn-2941	Polera / sudadera\tHoodie / sweatshirt	Polera / sudadera\tHoodie / sweatshirt	1	7.67	7.67
17	7	RIn-2941	Polera / sudadera\tHoodie / sweatshirt	Polera / sudadera\tHoodie / sweatshirt	1	7.67	7.67
18	8	RI-2175	Slippers - Pantuflas	Slippers - Pantuflas	100	9.01	901.00
19	8	RI-2089	Sostén - sujetador\tBrassier	Sostén - sujetador\tBrassier	100	12.47	1247.00
20	8	RIn-2481	Abrigo\tCoat	Abrigo\tCoat	100	13.27	1327.00
21	8	RI-2556	Sostén - sujetador deportivo	Sostén - sujetador deportivo	100	11.95	1195.00
22	8	RIn-2887	Camiseta de manga larga\tLong-sleeve	Camiseta de manga larga\tLong-sleeve	100	13.48	1348.00
23	8	RIn-2837	Cárdigan\tCardigan	Cárdigan\tCardigan	100	13.27	1327.00
24	8	RIn-2834	Casaca / chaqueta\tJacket	Casaca / chaqueta\tJacket	100	5.13	513.00
25	8	RIn-2432	Casaca / chaqueta de cuero\tLeather jacket	Casaca / chaqueta de cuero\tLeather jacket	100	13.95	1395.00
26	8	RIn-2128	Chaleco\tVest	Chaleco\tVest	100	6.66	666.00
27	8	RIn-2523	Gorro\tBeanie	Gorro\tBeanie	100	10.11	1011.00
28	9	RIn-2523	Gorro\tBeanie	Gorro\tBeanie	100	10.11	1011.00
29	9	RIn-2887	Camiseta de manga larga\tLong-sleeve	Camiseta de manga larga\tLong-sleeve	100	13.48	1348.00
30	9	RIn-2837	Cárdigan\tCardigan	Cárdigan\tCardigan	100	13.27	1327.00
31	9	RIn-2432	Casaca / chaqueta de cuero\tLeather jacket	Casaca / chaqueta de cuero\tLeather jacket	100	13.95	1395.00
32	9	RIn-2128	Chaleco\tVest	Chaleco\tVest	100	6.66	666.00
33	9	RIn-2523	Gorro\tBeanie	Gorro\tBeanie	100	10.11	1011.00
\.


--
-- Data for Name: detalle_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_factura (id_detalle_factura, det_cantidad, det_producto, det_factura, det_precio_unitario, det_iva, det_subtotal, det_precio_total) FROM stdin;
1	4	1	1	12.31	5.28	43.96	49.24
2	4	2	1	16.27	6.97	58.11	65.08
3	3	3	1	10.69	3.44	28.63	32.07
4	5	5	1	19.24	10.31	85.89	96.20
5	5	6	1	18.12	9.71	80.89	90.60
6	4	9	1	20.58	8.82	73.50	82.32
7	6	10	1	19.72	12.68	105.64	118.32
8	3	4	2	20.03	6.44	53.65	60.09
9	4	7	2	10.58	4.53	37.79	42.32
10	4	8	2	14.87	6.37	53.11	59.48
11	5	11	2	21.90	11.73	97.77	109.50
12	5	12	2	22.70	12.16	101.34	113.50
13	6	13	2	22.24	14.30	119.14	133.44
14	6	14	2	21.90	14.08	117.32	131.40
15	6	15	2	8.46	5.44	45.32	50.76
16	6	16	2	23.02	14.80	123.32	138.12
17	6	17	2	10.99	7.07	58.88	65.94
18	6	18	2	16.68	10.72	89.36	100.08
19	6	19	2	24.45	15.72	130.98	146.70
20	6	20	2	12.66	8.14	67.82	75.96
21	3	20	3	12.66	4.07	33.91	37.98
22	3	17	3	10.99	3.53	29.44	32.97
23	1	1	4	12.31	1.32	10.99	12.31
24	1	9	4	20.58	2.21	18.38	20.58
25	1	2	5	16.27	1.74	14.53	16.27
26	1	10	5	19.72	2.11	17.61	19.72
27	3	5	5	19.24	6.18	51.54	57.72
28	5	5	6	19.24	10.31	85.89	96.20
29	1	13	6	22.24	2.38	19.86	22.24
30	3	12	7	22.70	7.30	60.80	68.10
31	5	16	7	23.02	12.33	102.77	115.10
32	2	5	8	19.24	4.12	34.36	38.48
33	3	7	8	10.58	3.40	28.34	31.74
34	6	1	9	12.31	7.91	65.95	73.86
35	9	20	9	12.66	12.21	101.73	113.94
36	12	17	9	10.99	14.13	117.75	131.88
37	5	19	9	24.45	13.10	109.15	122.25
38	7	18	9	16.68	12.51	104.25	116.76
39	11	15	9	8.46	9.97	83.09	93.06
40	8	14	9	21.90	18.77	156.43	175.20
41	16	6	10	18.12	31.06	258.86	289.92
42	18	3	10	10.69	20.62	171.80	192.42
43	24	7	10	10.58	27.21	226.71	253.92
44	1	5	11	19.24	2.06	17.18	19.24
45	3	5	12	19.24	6.18	51.54	57.72
46	3	14	12	21.90	7.04	58.66	65.70
47	3	4	13	20.03	6.44	53.65	60.09
48	9	2	14	16.27	15.69	130.74	146.43
49	95	3	15	10.69	108.81	906.74	1015.50
50	112	4	16	20.03	240.36	2003.00	2243.40
51	51	9	17	20.58	112.45	937.12	1049.60
52	44	5	18	19.24	90.70	755.86	846.56
53	8	6	18	18.12	15.53	129.43	144.96
54	15	7	18	10.58	17.00	141.70	158.70
55	51	8	18	14.87	81.25	677.12	758.37
56	4	10	18	19.72	8.45	70.43	78.88
57	88	15	18	8.46	79.77	664.71	744.48
58	51	18	18	16.68	91.15	759.54	850.68
59	4	18	19	16.68	7.15	59.57	66.72
60	55	5	19	19.24	113.38	944.82	1058.20
61	3	5	20	19.24	6.18	51.54	57.72
62	95	6	20	18.12	184.44	1537.00	1721.40
63	72	7	20	10.58	81.62	680.14	761.76
64	5	8	21	14.87	7.97	66.38	74.35
65	3	9	21	20.58	6.62	55.13	61.74
66	10	12	21	22.70	24.32	202.68	227.00
67	3	13	21	22.24	7.15	59.57	66.72
68	7	12	22	22.70	17.03	141.88	158.91
69	10	11	22	21.90	23.47	195.54	219.00
70	11	13	22	22.24	26.21	218.43	244.64
71	1	15	22	8.46	0.91	7.55	8.46
72	51	17	22	10.99	60.05	500.44	560.49
73	5	17	23	10.99	5.89	49.06	54.95
\.


--
-- Data for Name: detalle_ingreso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_ingreso (id_detalle_ingreso, deting_factura_ingreso, deting_producto, deting_cantidad, deting_precio_unitario, deting_iva, deting_subtotal, deting_precio_total) FROM stdin;
1	1	4	111	12.14	12.00	1203.20	1347.50
2	1	7	109	6.41	12.00	623.83	698.69
3	1	8	109	9.01	12.00	876.87	982.09
4	1	11	100	13.27	12.00	1184.80	1327.00
5	1	12	98	13.76	12.00	1204.00	1348.50
6	1	14	105	13.27	12.00	1244.10	1393.30
7	1	15	100	5.13	12.00	458.04	513.00
8	1	16	108	13.95	12.00	1345.20	1506.60
9	1	17	106	6.66	12.00	630.32	705.96
10	1	18	108	10.11	12.00	974.89	1091.90
11	1	20	112	7.67	12.00	767.00	859.04
12	1	3	112	6.48	12.00	648.00	725.76
13	1	5	115	11.66	12.00	1197.20	1340.90
14	1	6	119	10.98	12.00	1166.60	1306.60
15	2	9	9	12.47	12.00	100.21	112.23
16	2	2	9	9.86	12.00	79.23	88.74
17	2	10	9	11.95	12.00	96.03	107.55
18	3	13	19	13.48	12.00	228.68	256.12
19	3	12	11	13.76	12.00	135.14	151.36
20	3	9	51	12.47	12.00	567.83	635.97
21	4	16	1	13.95	12.00	12.46	13.95
22	4	16	1	13.95	12.00	12.46	13.95
23	4	16	1	13.95	12.00	12.46	13.95
24	4	16	1	13.95	12.00	12.46	13.95
\.


--
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empresa (id_empresa, emp_ruc, emp_nombre_empresa, emp_gerente, emp_matriz, emp_sucursal, emp_pais, emp_provincia, emp_ciudad, emp_telefono, emp_email, emp_fecha_inicio) FROM stdin;
2	1003938478001	Pasamanería S.A.	1	48097 South Avenue	3274 Pearson Crossing	Ecuador	Pichincha	Calderon	1038705661	amcgettrick1@illinois.edu	2015-10-02
3	1003938479001	Terracotton Cía. Ltda. ( Ziro)	1	94 Macpherson Drive	66352 Katie Avenue	Ecuador	Pichincha	Quito	6821165749	edouch2@netvibes.com	2011-04-16
4	1003938470001	La Esperanza Comercializadora Wholesaleinn S.A.	1	0 Dexter Avenue	85 Spenser Junction	Ecuador	Pichincha	Cayambe	5147888360	sdarby3@va.gov	2014-11-20
1	1003938477001	ELENITA S.A.	1	163 Crownhardt Parkway	Ibarra San Francisco del Tejar	Ecuador	Pichincha	Quito	7675017505	ephelip0@illinois.edu	2019-11-26
\.


--
-- Data for Name: estado_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estado_pedido (id_estado_pedido, est_factura, est_valor_total, est_saldo, est_fecha_emision) FROM stdin;
6	12	123.42	108.42	2022-02-21
7	13	60.09	45.09	2022-02-21
4	9	826.95	0.00	2022-02-21
3	7	183.20	160.20	2022-02-20
5	10	736.26	566.26	2022-02-21
1	2	1227.29	137.00	2022-02-20
2	6	118.44	21.44	2022-02-20
8	22	1191.50	850.30	2022-02-24
\.


--
-- Data for Name: factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura (id_factura, fact_numero_factura, fact_fecha_emision, fact_fecha_remision, fact_fecha_autorizacion, fact_forma_pago, fact_tipo_factura, fact_cabecera_factura, fact_estado, fact_entregado, fact_descuento, fact_subtotal, fact_iva, fact_total) FROM stdin;
1	000-001	2022-02-20	2022-02-20	2022-02-20	1	3	1	t	t	0.00	476.63	57.20	533.83
2	000-002	2022-02-20	2022-02-20	2022-02-20	5	4	2	f	f	0.00	1095.79	131.49	1227.29
3	000-003	2022-02-20	2022-02-20	2022-02-20	2	1	3	t	t	0.00	63.35	7.60	70.95
4	000-004	2022-02-20	2022-02-20	2022-02-20	1	1	4	t	t	0.00	29.37	3.52	32.89
5	000-005	2022-02-20	2022-02-20	2022-02-20	1	1	5	t	t	0.00	83.67	10.04	93.71
6	000-006	2022-02-20	2022-02-20	2022-02-20	5	4	6	f	f	0.00	105.75	12.69	118.44
7	000-007	2022-02-20	2022-02-20	2022-02-20	5	4	7	f	f	0.00	163.57	19.63	183.20
8	000-008	2022-02-21	2022-02-21	2022-02-21	4	3	8	t	t	0.00	62.70	7.52	70.22
10	000-0010	2022-02-21	2022-02-21	2022-02-21	5	4	10	f	f	0.00	657.37	78.88	736.26
11	000-0011	2022-02-21	2022-02-21	2022-02-21	1	1	11	t	t	0.00	17.18	2.06	19.24
12	000-0012	2022-02-21	2022-02-21	2022-02-21	5	4	12	f	f	0.00	110.20	13.22	123.42
13	000-0013	2022-02-21	2022-02-21	2022-02-21	5	4	13	f	f	0.00	53.65	6.44	60.09
14	000-0014	2022-02-22	2022-02-22	2022-02-22	4	3	14	t	t	0.00	130.74	15.69	146.43
15	000-0015	2022-02-22	2022-02-22	2022-02-22	4	3	15	t	t	0.00	906.74	108.81	1015.50
16	000-0016	2022-02-22	2022-02-22	2022-02-22	1	1	17	t	t	0.00	2003.00	240.36	2243.40
17	000-0017	2022-02-22	2022-02-22	2022-02-22	1	1	18	t	t	0.00	937.12	112.45	1049.60
18	000-0018	2022-02-22	2022-02-22	2022-02-22	4	3	19	t	t	0.00	3198.79	383.85	3582.63
9	000-009	2022-02-21	2022-02-21	2022-02-21	5	4	9	t	f	0.00	738.35	88.60	826.95
19	000-0019	2022-02-22	2022-02-22	2022-02-22	4	3	20	t	t	0.00	1004.39	120.53	1124.92
20	000-0020	2022-02-22	2022-02-22	2022-02-22	2	1	21	t	t	0.00	2268.68	272.24	2540.88
21	000-0021	2022-02-24	2022-02-24	2022-02-24	3	3	22	t	t	0.00	383.76	46.05	429.81
22	000-0022	2022-02-24	2022-02-24	2022-02-24	5	4	23	f	f	0.00	1063.84	127.66	1191.50
23	000-0023	2022-02-24	2022-02-24	2022-02-24	5	3	24	t	t	0.00	49.06	5.89	54.95
\.


--
-- Data for Name: factura_ingreso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura_ingreso (id_factura_ingreso, facting_cabecera_ingreso, facting_subtotal, facting_iva, facting_total) FROM stdin;
1	1	13524.05	1622.79	15146.84
2	2	275.47	33.05	308.52
3	3	931.65	111.80	1043.45
4	4	49.82	5.98	55.80
\.


--
-- Data for Name: forma_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.forma_pago (id_forma_pago, fp_nombre) FROM stdin;
1	Pago mediante Cheque
2	Pago mediante Tarjeta de Crédito
3	Pago mediante Tarjeta de Débito
4	Pago mediante Transferencia Electrónica
5	Pago mediante Efectivo
\.


--
-- Data for Name: gerente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gerente (id_gerente, ger_usuario, ger_telefono, ger_email, ger_direccion, ger_cedula) FROM stdin;
1	ADM-1003938477	7023125225	elenita_gerente@elen.com.ec	Ibarra - El Tejar	1003938477
2	ADM-1001590650	0989028282	masalazara@utn.edu.ec	Av. Simon Bolivar Quito	1001590650
\.


--
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) FROM stdin;
10	RI-2556	5	Sostén - sujetador deportivo	Sostén - sujetador deportivo	2	4	11.95	12.00	19.72		4
18	RIn-2523	49	Gorro\tBeanie	Gorro\tBeanie	3	6	10.11	12.00	16.68		3
5	RI-2676	0	Camisón\tNightgown - camisole	Camisón\tNightgown - camisole	2	4	11.66	12.00	19.24		3
7	RI-2818	0	Pijama\tPajamas - Pyjamas	Pijama\tPajamas - Pyjamas	4	4	6.41	12.00	10.58		2
6	RI-2226	0	Pantimedias\tPantyhose - stocking	Pantimedias\tPantyhose - stocking	3	4	10.98	12.00	18.12		2
8	RI-2175	52	Slippers - Pantuflas	Slippers - Pantuflas	4	4	9.01	12.00	14.87		3
1	RI-2245	0	Bata - Robe	Bata - Robe	3	4	7.46	12.00	12.31		4
20	RIn-2941	104	Polera / sudadera\tHoodie / sweatshirt	Polera / sudadera\tHoodie / sweatshirt	1	4	7.67	12.00	12.66		3
9	RI-2089	11	Sostén - sujetador\tBrassier	Sostén - sujetador\tBrassier	3	4	12.47	12.00	20.58		5
12	RIn-2012	88	Botas\tBoots	Botas\tBoots	2	6	13.76	12.00	22.70		3
13	RIn-2887	5	Camiseta de manga larga\tLong-sleeve	Camiseta de manga larga\tLong-sleeve	3	5	13.48	12.00	22.24		4
15	RIn-2834	0	Casaca / chaqueta\tJacket	Casaca / chaqueta\tJacket	4	5	5.13	12.00	8.46		2
11	RIn-2481	87	Abrigo\tCoat	Abrigo\tCoat	1	5	13.27	12.00	21.90		3
17	RIn-2128	34	Chaleco\tVest	Chaleco\tVest	2	5	6.66	12.00	10.99		4
19	RIn-2288	0	Impermeable\tRaincoat	Impermeable\tRaincoat	2	6	14.82	12.00	24.45		3
16	RIn-2432	107	Casaca / chaqueta de cuero\tLeather jacket	Casaca / chaqueta de cuero\tLeather jacket	4	5	13.95	12.00	23.02		2
14	RIn-2837	96	Cárdigan\tCardigan	Cárdigan\tCardigan	4	6	13.27	12.00	21.90		5
2	RI-2331	0	Calcetines - medias\tSocks	Calcetines - medias\tSocks	1	4	9.86	12.00	16.27		1
3	RI-2092	0	Calzoncillos ceñidosBr - iefs	Calzoncillos ceñidosBr - iefs	3	4	6.48	12.00	10.69		6
4	RI-2149	0	Calzoncillos sueltos - Boxers	Calzoncillos sueltos - Boxers	2	4	12.14	12.00	20.03		7
\.


--
-- Data for Name: talla_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.talla_producto (id_talla_producto, talla_nombre) FROM stdin;
1	XS (40-42)
2	S (44-46)
3	M (48-50)
4	L (52-54)
5	XL (56-58)
6	2XL (60-62)
7	3XL (64-66)
\.


--
-- Data for Name: tipo_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_factura (id_tipo_factura, tf_nombre) FROM stdin;
1	Factura de pago a Crédito
2	Factura Anulada
3	Factura de pago a Contado
4	Factura de venta por Anticipación
\.


--
-- Data for Name: tipo_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_producto (id_tipo_producto, tp_nombre) FROM stdin;
1	Ropa de embarazada
2	Ropa de género
3	Ropa de talles especiales
4	Ropa interior
5	Ropa de trabajo
6	Ropa de abrigo
7	Ropa deportiva
8	Ropa informal
9	Ropa de etiqueta
\.


--
-- Data for Name: tipo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_usuario (id_tipo_usuario, tipus_nombre) FROM stdin;
1	Gerente Empresarial
2	Vendedor de Productos
3	Cientes de la Empresa
4	Cajero de la Empresa
5	Proveedor de Productos
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, us_email, us_password, us_activo, us_nombres, us_apellidos, us_fecha_registro, us_tipousuario) FROM stdin;
ADM-1003938477	elenita_admin@gmail.com	xikF42WbD9QhYz8Vy0td8w==	t	Elena Anabel	Rueda Carlosama	2022-05-27	1
ADM-1001590650	clarity@utn.edu.ec	xikF42WbD9QhYz8Vy0td8w==	t	Clarity June	July Esthers	2022-05-27	1
VEN-1001590651	carlarodriguez@gmail.com	mDHQRDcpgChRZFCoAKOCBw==	t	Rodriguez	Carla	2022-02-22	2
CLI-1003938410	mario@utn.edu.ec	4T+qwpaaQ7zRyWrYg41Vnw==	f	Mario	Salazar	2022-02-20	3
CLI-1003938477	elenita_cli@gmail.com	4T+qwpaaQ7yXlzU3I6pApw==	f	Elena Anabel	Rueda Carlosama	2022-02-21	3
CLI-0203656987	roblespedro@hotmail.com	K2o4ogh3xexl7ZdZ1ayvJA==	t	Pedro	Robles	2022-02-24	3
VEN-1003938477	elenita_vend@gravatar.com	Ofrn8mXdeBYDrYOwTY+d49gUtdv+Z2gy	t	Elena Anabel	Rueda Carlosama	2022-05-27	2
CLI-1001590650	clarity@gmail.com	t+aYiJSVsAZ667+8oXbFFQ==	t	June	Clarity	2022-02-20	3
CLI-9999999999	**********************	IXlU/ahD3uiBXxJeI1Sm3w==	f	Consumidor Final	Consumidor Final	2022-02-24	3
VEN-1001590650	clarityjune@gmail.com	+tQUVdl38r1OCO/MqwWQtA==	f	June	Clarity	2022-02-22	2
VEN-1003938410	mario_vend@gravatar.com	Ofrn8mXdeBY8PdkZ2ZjNDw==	t	Mario Andres	Salazar Anrango	2022-05-27	2
CLI-1003727060	inessalazar@gmail.com	4T+qwpaaQ7xlDabhLqSImw==	t	Ines	Salazar	2022-02-20	3
CLI-1003639584	lizbeth_cli@hotmail.com	4T+qwpaaQ7x1wNJQNfyKyA==	t	Lizbeth Anahy	Suarez Lopez	2022-02-21	3
\.


--
-- Data for Name: vendedor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vendedor (id_vendedor, ven_usuario, ven_telefono, ven_email, ven_direccion, ven_cedula, ven_sueldo, ven_fecha_inicio) FROM stdin;
2	VEN-1003938477	4921322018	elenita_vend@gravatar.com	San Francisco del Tejar	1003938477	674.31	2021-05-13
1	VEN-1003938410	0989061898	mario_vend@gravatar.com	Ibarra-El Tejar	1003938410	673.27	2021-05-13
4	VEN-1001590650	0989061898	clarityjune@gmail.com	Atuntaqui	1001590650	425.00	2022-02-22
3	VEN-1001590651	0989061898	carlarodriguez@gmail.com	Quito - Pichincha	1001590651	425.00	2022-02-22
\.


--
-- Name: cabecera_compra_id_cabecera_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cabecera_compra_id_cabecera_compra_seq', 15, true);


--
-- Name: cabecera_factura_id_cabecera_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cabecera_factura_id_cabecera_factura_seq', 24, true);


--
-- Name: cabecera_ingreso_id_cabecera_ingreso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cabecera_ingreso_id_cabecera_ingreso_seq', 4, true);


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 8, true);


--
-- Name: compra_producto_id_compra_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_producto_id_compra_producto_seq', 9, true);


--
-- Name: detalle_abono_id_detalle_abono_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_abono_id_detalle_abono_seq', 29, true);


--
-- Name: detalle_compra_id_detalle_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_compra_id_detalle_compra_seq', 33, true);


--
-- Name: detalle_factura_id_detalle_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_factura_id_detalle_factura_seq', 73, true);


--
-- Name: detalle_ingreso_id_detalle_ingreso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_ingreso_id_detalle_ingreso_seq', 24, true);


--
-- Name: empresa_id_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empresa_id_empresa_seq', 1, false);


--
-- Name: estado_pedido_id_estado_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_pedido_id_estado_pedido_seq', 8, true);


--
-- Name: factura_id_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.factura_id_factura_seq', 23, true);


--
-- Name: factura_ingreso_id_factura_ingreso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.factura_ingreso_id_factura_ingreso_seq', 4, true);


--
-- Name: forma_pago_id_forma_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.forma_pago_id_forma_pago_seq', 1, false);


--
-- Name: gerente_id_gerente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.gerente_id_gerente_seq', 1, false);


--
-- Name: producto_id_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.producto_id_producto_seq', 1, false);


--
-- Name: talla_producto_id_talla_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.talla_producto_id_talla_producto_seq', 1, false);


--
-- Name: tipo_factura_id_tipo_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_factura_id_tipo_factura_seq', 1, false);


--
-- Name: tipo_producto_id_tipo_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_producto_id_tipo_producto_seq', 1, false);


--
-- Name: tipo_usuario_id_tipo_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_usuario_id_tipo_usuario_seq', 1, false);


--
-- Name: vendedor_id_vendedor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vendedor_id_vendedor_seq', 4, true);


--
-- Name: cliente cliente_cli_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_codigo_key UNIQUE (cli_codigo);


--
-- Name: cliente cliente_cli_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_email_key UNIQUE (cli_email);


--
-- Name: cliente cliente_cli_ruc_cedula_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_ruc_cedula_key UNIQUE (cli_ruc_cedula);


--
-- Name: cliente cliente_cli_usuario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_usuario_key UNIQUE (cli_usuario);


--
-- Name: empresa empresa_emp_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_emp_email_key UNIQUE (emp_email);


--
-- Name: factura factura_fact_numero_factura_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_fact_numero_factura_key UNIQUE (fact_numero_factura);


--
-- Name: gerente gerente_ger_cedula_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_ger_cedula_key UNIQUE (ger_cedula);


--
-- Name: gerente gerente_ger_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_ger_email_key UNIQUE (ger_email);


--
-- Name: gerente gerente_ger_usuario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_ger_usuario_key UNIQUE (ger_usuario);


--
-- Name: cabecera_compra pk_cab_comp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_compra
    ADD CONSTRAINT pk_cab_comp PRIMARY KEY (id_cabecera_compra);


--
-- Name: cabecera_factura pk_cab_fact; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_factura
    ADD CONSTRAINT pk_cab_fact PRIMARY KEY (id_cabecera_factura);


--
-- Name: cabecera_ingreso pk_cabing; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_ingreso
    ADD CONSTRAINT pk_cabing PRIMARY KEY (id_cabecera_ingreso);


--
-- Name: cliente pk_cliente; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (id_cliente);


--
-- Name: compra_producto pk_cp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra_producto
    ADD CONSTRAINT pk_cp PRIMARY KEY (id_compra_producto);


--
-- Name: detalle_abono pk_det_ab; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_abono
    ADD CONSTRAINT pk_det_ab PRIMARY KEY (id_detalle_abono);


--
-- Name: detalle_compra pk_det_comp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compra
    ADD CONSTRAINT pk_det_comp PRIMARY KEY (id_detalle_compra);


--
-- Name: detalle_factura pk_det_fact; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT pk_det_fact PRIMARY KEY (id_detalle_factura);


--
-- Name: detalle_ingreso pk_deting; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ingreso
    ADD CONSTRAINT pk_deting PRIMARY KEY (id_detalle_ingreso);


--
-- Name: empresa pk_empresa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT pk_empresa PRIMARY KEY (id_empresa);


--
-- Name: estado_pedido pk_est_ped; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_pedido
    ADD CONSTRAINT pk_est_ped PRIMARY KEY (id_estado_pedido);


--
-- Name: factura_ingreso pk_facing; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_ingreso
    ADD CONSTRAINT pk_facing PRIMARY KEY (id_factura_ingreso);


--
-- Name: factura pk_factura; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT pk_factura PRIMARY KEY (id_factura);


--
-- Name: forma_pago pk_forma_pago; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_pago
    ADD CONSTRAINT pk_forma_pago PRIMARY KEY (id_forma_pago);


--
-- Name: gerente pk_gerente; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT pk_gerente PRIMARY KEY (id_gerente);


--
-- Name: producto pk_producto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT pk_producto PRIMARY KEY (id_producto);


--
-- Name: talla_producto pk_talla; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talla_producto
    ADD CONSTRAINT pk_talla PRIMARY KEY (id_talla_producto);


--
-- Name: tipo_factura pk_tip_fact; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_factura
    ADD CONSTRAINT pk_tip_fact PRIMARY KEY (id_tipo_factura);


--
-- Name: tipo_producto pk_tip_prod; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_producto
    ADD CONSTRAINT pk_tip_prod PRIMARY KEY (id_tipo_producto);


--
-- Name: tipo_usuario pk_tip_us; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_usuario
    ADD CONSTRAINT pk_tip_us PRIMARY KEY (id_tipo_usuario);


--
-- Name: usuario pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (id_usuario);


--
-- Name: vendedor pk_vendedor; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT pk_vendedor PRIMARY KEY (id_vendedor);


--
-- Name: producto producto_prod_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_prod_codigo_key UNIQUE (prod_codigo);


--
-- Name: usuario usuario_us_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_us_email_key UNIQUE (us_email);


--
-- Name: vendedor vendedor_ven_cedula_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT vendedor_ven_cedula_key UNIQUE (ven_cedula);


--
-- Name: vendedor vendedor_ven_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT vendedor_ven_email_key UNIQUE (ven_email);


--
-- Name: vendedor vendedor_ven_usuario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT vendedor_ven_usuario_key UNIQUE (ven_usuario);


--
-- Name: factura fk_cab_fact_fact; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_cab_fact_fact FOREIGN KEY (fact_cabecera_factura) REFERENCES public.cabecera_factura(id_cabecera_factura);


--
-- Name: factura_ingreso fk_cabing_facting; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_ingreso
    ADD CONSTRAINT fk_cabing_facting FOREIGN KEY (facting_cabecera_ingreso) REFERENCES public.cabecera_ingreso(id_cabecera_ingreso);


--
-- Name: compra_producto fk_cc_cp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra_producto
    ADD CONSTRAINT fk_cc_cp FOREIGN KEY (comprod_cabecera_compra) REFERENCES public.cabecera_compra(id_cabecera_compra);


--
-- Name: cabecera_factura fk_cli_cabfact; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_factura
    ADD CONSTRAINT fk_cli_cabfact FOREIGN KEY (cab_cliente) REFERENCES public.cliente(id_cliente);


--
-- Name: detalle_abono fk_cli_da; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_abono
    ADD CONSTRAINT fk_cli_da FOREIGN KEY (detab_cliente) REFERENCES public.cliente(id_cliente);


--
-- Name: detalle_compra fk_cp_dcomp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compra
    ADD CONSTRAINT fk_cp_dcomp FOREIGN KEY (detcomp_compra_producto) REFERENCES public.compra_producto(id_compra_producto);


--
-- Name: cabecera_factura fk_emp_cabfact; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_factura
    ADD CONSTRAINT fk_emp_cabfact FOREIGN KEY (cab_empresa) REFERENCES public.empresa(id_empresa);


--
-- Name: cabecera_ingreso fk_emp_cabing; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_ingreso
    ADD CONSTRAINT fk_emp_cabing FOREIGN KEY (cabing_empresa) REFERENCES public.empresa(id_empresa);


--
-- Name: cabecera_compra fk_emp_cc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_compra
    ADD CONSTRAINT fk_emp_cc FOREIGN KEY (cabcomp_proveedor) REFERENCES public.empresa(id_empresa);


--
-- Name: producto fk_emp_prod; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk_emp_prod FOREIGN KEY (prod_proveedor) REFERENCES public.empresa(id_empresa);


--
-- Name: detalle_abono fk_ep_da; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_abono
    ADD CONSTRAINT fk_ep_da FOREIGN KEY (detab_estado_pedido) REFERENCES public.estado_pedido(id_estado_pedido);


--
-- Name: detalle_factura fk_fact_detf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT fk_fact_detf FOREIGN KEY (det_factura) REFERENCES public.factura(id_factura);


--
-- Name: estado_pedido fk_fact_ep; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_pedido
    ADD CONSTRAINT fk_fact_ep FOREIGN KEY (est_factura) REFERENCES public.factura(id_factura);


--
-- Name: detalle_ingreso fk_facting_deting; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ingreso
    ADD CONSTRAINT fk_facting_deting FOREIGN KEY (deting_factura_ingreso) REFERENCES public.factura_ingreso(id_factura_ingreso);


--
-- Name: factura fk_fp_fact; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_fp_fact FOREIGN KEY (fact_forma_pago) REFERENCES public.forma_pago(id_forma_pago);


--
-- Name: empresa fk_ger_emp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT fk_ger_emp FOREIGN KEY (emp_gerente) REFERENCES public.gerente(id_gerente);


--
-- Name: detalle_factura fk_prod_detf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT fk_prod_detf FOREIGN KEY (det_producto) REFERENCES public.producto(id_producto);


--
-- Name: detalle_ingreso fk_prod_deting; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ingreso
    ADD CONSTRAINT fk_prod_deting FOREIGN KEY (deting_producto) REFERENCES public.producto(id_producto);


--
-- Name: cabecera_ingreso fk_prov_cabing; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_ingreso
    ADD CONSTRAINT fk_prov_cabing FOREIGN KEY (cabing_proveedor) REFERENCES public.empresa(id_empresa);


--
-- Name: producto fk_talla_prod; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk_talla_prod FOREIGN KEY (prod_talla_producto) REFERENCES public.talla_producto(id_talla_producto);


--
-- Name: factura fk_tf_fact; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_tf_fact FOREIGN KEY (fact_tipo_factura) REFERENCES public.tipo_factura(id_tipo_factura);


--
-- Name: producto fk_tp_prod; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk_tp_prod FOREIGN KEY (prod_tipo_producto) REFERENCES public.tipo_producto(id_tipo_producto);


--
-- Name: usuario fk_tus_us; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_tus_us FOREIGN KEY (us_tipousuario) REFERENCES public.tipo_usuario(id_tipo_usuario);


--
-- Name: cliente fk_us_client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_us_client FOREIGN KEY (cli_usuario) REFERENCES public.usuario(id_usuario);


--
-- Name: vendedor fk_us_vend; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT fk_us_vend FOREIGN KEY (ven_usuario) REFERENCES public.usuario(id_usuario);


--
-- Name: gerente fk_us_vend; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT fk_us_vend FOREIGN KEY (ger_usuario) REFERENCES public.usuario(id_usuario);


--
-- Name: cabecera_factura fk_vend_cabfact; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_factura
    ADD CONSTRAINT fk_vend_cabfact FOREIGN KEY (cab_vendedor) REFERENCES public.vendedor(id_vendedor);


--
-- Name: cabecera_ingreso fk_vend_cabing; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_ingreso
    ADD CONSTRAINT fk_vend_cabing FOREIGN KEY (cabing_vendedor) REFERENCES public.vendedor(id_vendedor);


--
-- Name: cabecera_compra fk_vend_cc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cabecera_compra
    ADD CONSTRAINT fk_vend_cc FOREIGN KEY (cabcomp_vendedor) REFERENCES public.vendedor(id_vendedor);


--
-- Name: detalle_abono fk_vend_da; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_abono
    ADD CONSTRAINT fk_vend_da FOREIGN KEY (detab_vendedor) REFERENCES public.vendedor(id_vendedor);


--
-- PostgreSQL database dump complete
--

