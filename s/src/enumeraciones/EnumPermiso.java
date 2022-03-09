package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumPermiso {

    //Menues
    VISUALIZACION                       (    0, "Permisos de visualización"	    , null, 0),
    
    COMPRAS                             (  10000, "Compras"			    , VISUALIZACION),
        COMPRAS_ORDEN_COMPRA		(  10010, "Ordenes de compra"		    , COMPRAS),
        COMPRAS_VISITA	                (  10020, "Visitas a proveedores"	    , COMPRAS),
        COMPRAS_DESEMPEÑO_LOGISTICO	(  10030, "Desempeño logístico"		    , COMPRAS),
        COMPRAS_MONEDA                  (  10040, "Monedas"			    , COMPRAS),
        COMPRAS_EXTERNOS                (  10070, "Externos: datos y evaluaciones"  , COMPRAS),

    VENTAS                              (  20000, "Ventas"			    , VISUALIZACION),
        VENTAS_ORDEN_COMPRA		(  20010, "Ordenes de compra"		    , VENTAS),
        VENTAS_PARAMETRO_COSTO		(  20020, "Parámetros de costos"	    , VENTAS),
        VENTAS_VISITA			(  20030, "Visitas a clientes"		    , VENTAS),
        VENTAS_MONEDA			(  20040, "Monedas"			    , VENTAS),

    PRODUCCION                          (  30000, "Producción"			    , VISUALIZACION),
        PRODUCCION_LOTE			(  30010, "Partidas"			    , PRODUCCION),
        PRODUCCION_PLANIFICACIÓN	(  30020, "Planificación"		    , PRODUCCION),
        PRODUCCION_TIPO_PROCESO		(  30030, "Tipos de procesos"		    , PRODUCCION),
        PRODUCCION_MONITOR		(  30040, "Monitores"   		    , PRODUCCION),

    MANTENIMIENTO                       (  40000, "Mantenimiento"		    , VISUALIZACION),
        MANTENIMIENTO_TODO              (  40010, "Todos los niveles"		    , MANTENIMIENTO),
        MANTENIMIENTO_NIVEL2            (  40020, "Nivel 2"			    , MANTENIMIENTO),
        MANTENIMIENTO_NIVEL3            (  40030, "Nivel 3"			    , MANTENIMIENTO),
        REPUESTOS                       (  40050, "Repuestos"			    , MANTENIMIENTO),

    LOGISTICA                           (  50000, "Logística"			    , VISUALIZACION),
        REMITO_DE_PROVEEDOR		(  50010, "Remito de proveedores"	    , LOGISTICA),
        REMITO_A_CONTRATISTA		(  50020, "Remitos a contratistas"	    , LOGISTICA),
        REMITO_DE_CONTRATISTA		(  50030, "Remitos de contratistas"	    , LOGISTICA),
        REMITO_A_CLIENTE			(  50040, "Remito a clientes"		    , LOGISTICA),
        REMITO_INTERNO			(  50050, "Remitos internos"		    , LOGISTICA),
        REMITO_PRODUCTIVO		(  50060, "Remito productivos"		    , LOGISTICA),
        REMITO_AJUSTE			(  50070, "Ajustes"			    , LOGISTICA),
        REMITO_LIBERACION		(  50080, "Liberaciones"		    , LOGISTICA),
        PEDIDO_A_PROVEEDOR		(  50090, "Pedidos a proveedores"	    , LOGISTICA),
        PEDIDO_DE_CLIENTE		(  50100, "Pedidos de clientes"		    , LOGISTICA),
        ENTREGA				(  50110, "Entregas"			    , LOGISTICA),
        ALMACEN				(  50120, "Almacenes"			    , LOGISTICA),
        PUERTO				(  50130, "Puertos y embalaje"		    , LOGISTICA),
        ETIQUETA				(  50140, "Etiquetas"			    , LOGISTICA),
        VISITA_DE_LOGISTICA		(  50150, "Visitas de logística"	    , LOGISTICA),
        MOVIMIENTO_DE_BARRA		(  50160, "Movimiento interno de barras"    , LOGISTICA),

    CALIDAD				(  60000, "Calidad"			    , VISUALIZACION),
	AUDITORIA_DE_PRODUCTOS		(  60010, "Auditorías de productos"	    , CALIDAD),
        INSTRUMENTO_MEDICION		(  60020, "Instrumentos de medición"	    , CALIDAD),
	PLAN_DE_CALIBRACION		(  60030, "Planes de calibración"	    , CALIDAD),
	RyR		        	(  60040, "RyR"				    , CALIDAD),
        NO_CONFORMIDAD			(  60050, "No conformidades y acciones"	    , CALIDAD),
	PLANIFICACION_DE_CALIDAD	(  60060, "Planificación"		    , CALIDAD),
	CERTIFICADO        		(  60070, "Certificados"		    , CALIDAD),
	ICPM			   	(  60080, "ICPM"			    , CALIDAD),
	DOCUMENTOS        		(  60090, "Documentos"			    , CALIDAD),
	VISITAS_CALIDAD    		(  60100, "Visitas a/de externos"	    , CALIDAD),
        TABLAS_BASICAS			(  60110, "Tablas básicas"		    , CALIDAD),
        AMFE				(  60120, "Amfe"			    , CALIDAD),
        FALLAS_INTERNAS     		(  60150, "Fallas internas de calidad"      , CALIDAD),

    MEDIO_AMBIENTE			(  70000, "Medio ambiente"		    , VISUALIZACION),
	ASPECTOS_E_IMPACTOS_MA		(  70010, "Aspectos e impactos"		    , MEDIO_AMBIENTE),
	MEDIDAS_LEGALES                 (  70030, "Mediciones legales"		    , MEDIO_AMBIENTE),

    SALUD_Y_SEGURIDAD			(  80000, "Salud y seguridad"		    , VISUALIZACION),
	ASPECTOS_E_IMPACTOS_SS		(  80010, "Aspectos e impactos"		    , SALUD_Y_SEGURIDAD),

    INGENIERIA				(  90000, "Ingeniería"			    , VISUALIZACION),
	ROSCAS				(  90010, "Roscas"			    , INGENIERIA),
	MATERIALES			(  90010, "Materiales"			    , INGENIERIA),
		
    CONTABILIDAD			(100000, "Contabilidad"			    , VISUALIZACION),
	FACTURA_DE_PROVEEDOR		(100010, "Facturas de proveedores"	    , CONTABILIDAD),
	FACTURA_A_CLIENTE		(100020, "Facturas a clientes"		    , CONTABILIDAD),
	FACTURA_DE_EXPORTACION		(100030, "Facturas de exportación"	    , CONTABILIDAD),
	NOTA_CREDITO_A_PROVEEDOR		(100040, "Notas de crédito a proveedores"   , CONTABILIDAD),
	NOTA_CREDITO_DE_PROVEEDOR	(100050, "Notas de crédito de proveedores"  , CONTABILIDAD),
	NOTA_CREDITO_A_CLIENTE		(100060, "Notas de crédito a clientes"	    , CONTABILIDAD),
	NOTA_CREDITO_DE_CLIENTE		(100070, "Notas de crédito de clientes"	    , CONTABILIDAD),
	NOTA_DEBITO_A_PROVEEDOR		(100080, "Notas de débito a proveedores"    , CONTABILIDAD),
	NOTA_DEBITO_DE_PROVEEDOR		(100090, "Notas de débito de proveedores"   , CONTABILIDAD),
	NOTA_DEBITO_A_CLIENTE		(100100, "Notas de débito a clientes"	    , CONTABILIDAD),
	NOTA_DEBITO_DE_CLIENTE		(100110, "Notas de débito de clientes"	    , CONTABILIDAD),
	RECIBO_DE_PROVEEDOR		(100120, "Recibos de proveedores"	    , CONTABILIDAD),
	RECIBO_A_CLIENTE			(100130, "Recibos a clientes"		    , CONTABILIDAD),
	RETENCION_IIBB			(100140, "Retenciones de II.BB."	    , CONTABILIDAD),
	RETENCION_GANANCIA		(100150, "Retenciones de ganancias"	    , CONTABILIDAD),
	CUENTA_CORRIENTE			(100160, "Cuentas corrientes"		    , CONTABILIDAD),
	DATOS				(100170, "Datos"			    , CONTABILIDAD),
	DATOS_FACTURACION       	(100180, "Datos de facturacion"             , CONTABILIDAD),
	CHEQUES                    	(100190, "Cheques"                          , CONTABILIDAD),
	CUENTAS                   	(100200, "Cuentas - Ejercicios contables"   , CONTABILIDAD),
        COBROS_PENDIENTES              	(100210, "Cobros pendientes"                , CONTABILIDAD),
        PAGO_A_PROVEEDOR              	(100300, "Pagos a proveedores"              , CONTABILIDAD),
        PAGO_DE_CLIENTE              	(100310, "Pagos a proveedores"              , CONTABILIDAD),
        LIBRO_DIARIO                   	(100400, "Libro diario"                     , CONTABILIDAD),
        LIBRO_IVA                   	(100410, "Libro IVA"                        , CONTABILIDAD),
        PARAMETRIZACION                	(100500, "Parametrización de asientos"       , CONTABILIDAD),
        
    RRHH				(110000, "Recursos humanos"		    , VISUALIZACION),
	USUARIOS				(110010, "Usuarios"			    , RRHH),
	CAPACITACION			(110020, "Capacitación"			    , RRHH),
	DEFINICION_RECIBOS_DE_SUELDO	(110030, "Definición de recibos de sueldo"  , RRHH),
	LIQUIDACIONES_DE_SUELDOS		(110040, "Liquidación de sueldos"	    , RRHH),
	RECIBOS_DE_SUELDOS		(110050, "Recibos de sueldo"		    , RRHH),
	CONTROL_DE_ASISTENCIA		(110060, "Control de asistencia"	    , RRHH),
	PERSONAL_EN_PLANTA		(110070, "Personal en planta"		    , RRHH),
	SUGERENCIAS			(110080, "Sugerencias del personal"	    , RRHH),
        RRHH_GANANCIAS              	(110100, "Deducciones de gananacias"	    , RRHH),
        RRHH_DEPARTAMENTOS             	(110200, "Departamentos y roles"	    , RRHH),
        
    DIRECCION				(120000, "Dirección"			    , VISUALIZACION),
	REVISIONES_POR_DIRECCION	(120010, "Revisiones del sistema de gestión", DIRECCION),
	PLAN_DE_MEJORAS			(120020, "Plan de mejoras"		    , DIRECCION),
        PLAN_DE_MEJORAS_COSTO		(120021, "Plan de mejoras: ver costos"	    , DIRECCION),
	VISITAS				(120030, "Visitas"			    , DIRECCION),
	INDICADORES                     (120040, "Indicadores de gestión"	    , DIRECCION),
    
    UTILES				(130050, "Útiles"			    , VISUALIZACION),
	TAREAS				(130010, "Tareas"			    , UTILES),
	AGENDA				(130020, "Agenda"			    , UTILES),
	CALENDARIO			(130030, "Calendario"			    , UTILES),
	CAMARAS                         (130035, "Cámaras de seguridad"     	    , UTILES),
	DATOS_DE_EMPRESA			(130040, "Datos de la empresa"		    , UTILES),
	NOTAS				(130050, "Notas"			    , UTILES),
	VERSIONES			(130060, "Versiones"			    , UTILES),
	PERMISOS_DE_USUARIOS		(130070, "Permisos de usuarios"		    , UTILES),
	LOG				(130080, "Registro de eventos"		    , UTILES),
	CAMBIAR_CONTRASEÑA		(130090, "Cambiar contraseña"		    , UTILES),
	TERMINALES			(130100, "Terminales"			    , UTILES),
	CERRAR_SESION			(130110, "Cerrar sesión de usuario"	    , UTILES),
	CERRAR_SISTEMA			(130120, "Cerrar sistema"		    , UTILES),
	PERSONALIZAR			(130130, "Personalizar el sistema"	    , UTILES),
        DATOS_DEL_SISTEMA		(130150, "Parámetros del sistema"	    , UTILES),
        LISTAS_NUMERICAS                (130160, "Listas numéricas"                 , UTILES),

		
		
	//****************  Acciones ************************
    ACCIONES				(5000000, "Acciones"			    , null, 1),
    
    A_ACCESO_DIA				(510000, "Acceso el personal: días"	    , ACCIONES),
        ACCESO_DIA_CREAR			(510010, "Crear"			    , A_ACCESO_DIA),
        ACCESO_DIA_EDITAR		(510020, "Editar"			    , A_ACCESO_DIA),
        ACCESO_DIA_BORRAR		(510030, "Borrar"			    , A_ACCESO_DIA),

    A_ACCESO_HORA			(520010, "Acceso del personal: horas"	    , ACCIONES),
        ACCESO_HORA_CREAR		(520020, "Crear"			    , A_ACCESO_HORA),
        ACCESO_HORA_EDITAR		(520030, "Editar"			    , A_ACCESO_HORA),
        ACCESO_HORA_BORRAR		(520040, "Borrar"			    , A_ACCESO_HORA),
	
    A_ACCIDENTE				(530000, "Accidentes"			    , ACCIONES),
        ACCIDENTE_CREAR			(530010, "Crear"			    , A_ACCIDENTE),
        ACCIDENTE_EDITAR			(530010, "Editar"			    , A_ACCIDENTE),
        ACCIDENTE_BORRAR			(530020, "Borrar"			    , A_ACCIDENTE),

    A_ACCION				(540000, "Acciones y no conf."		    , ACCIONES),
        ACCION_CREAR			(540010, "Crear"			    , A_ACCION),
        ACCION_EDITAR			(540020, "Editar"			    , A_ACCION),
        ACCION_BORRAR			(540030, "Borrar"			    , A_ACCION),
        ACCION_CERRAR			(540040, "Cerrar"			    , A_ACCION),

    A_ACCION_TIPO			(550000, "Acciones y no conf.: tipos"	    , ACCIONES),
        ACCION_TIPO_CREAR		(550010, "Crear"			    , A_ACCION_TIPO),
        ACCION_TIPO_EDITAR		(550020, "Editar"			    , A_ACCION_TIPO),
        ACCION_TIPO_BORRAR		(550030, "Borrar"			    , A_ACCION_TIPO),

    A_AGENDA			     	(560000, "Agenda"			    , ACCIONES),
        AGENDA_CREAR			(560010, "Crear"			    , A_AGENDA),
        AGENDA_EDITAR			(560020, "Editar"			    , A_AGENDA),
        AGENDA_BORRAR			(560030, "Borrar"			    , A_AGENDA),

    A_AGENDA_RUBRO			(570000, "Agenda: rubros"		    , ACCIONES),
        AGENDA_RUBRO_CREAR	    	(570010, "Crear"			    , A_AGENDA_RUBRO),
        AGENDA_RUBRO_EDITAR		(570020, "Editar"			    , A_AGENDA_RUBRO),
        AGENDA_RUBRO_BORRAR		(570030, "Borrar"			    , A_AGENDA_RUBRO),

    A_AMFE				(575000, "A.M.F.E."			    , ACCIONES),
        AMFE_CREAR		    	(575010, "Crear"			    , A_AMFE),
        AMFE_EDITAR			(575020, "Editar"			    , A_AMFE),
        AMFE_BORRAR			(575030, "Borrar"			    , A_AMFE),

    A_ARTICULO				(580000, "Artículos"			    , ACCIONES),
        ARTICULO_CREAR			(580010, "Crear"			    , A_ARTICULO),
        ARTICULO_EDITAR			(580020, "Editar"			    , A_ARTICULO),
        ARTICULO_BORRAR			(580030, "Borrar"			    , A_ARTICULO),

    A_ARTICULO_TIPO			(590000, "Artículos: tipos"		    , ACCIONES),
        ARTICULO_TIPO_CREAR		(590010, "Crear"			    , A_ARTICULO_TIPO),
        ARTICULO_TIPO_EDITAR		(590020, "Editar"			    , A_ARTICULO_TIPO),
        ARTICULO_TIPO_BORRAR		(590030, "Borrar"			    , A_ARTICULO_TIPO),

    A_AUDITORIA				(600000, "Auditorías de producto"	    , ACCIONES),
        AUDITORIA_CREAR			(600010, "Crear"			    , A_AUDITORIA),
        AUDITORIA_EDITAR			(600020, "Editar"			    , A_AUDITORIA),
        AUDITORIA_BORRAR			(600030, "Borrar"			    , A_AUDITORIA),

    A_BANCO                		(605000, "Bancos"                          , ACCIONES),
        BANCO_CREAR                     (605010, "Crear"                            , A_BANCO),
        BANCO_EDITAR                    (605020, "Editar"			    , A_BANCO),
        BANCO_BORRAR                    (605030, "Borrar"			    , A_BANCO),
        
        
    A_CALIBRACION			(610000, "Calibraciones"		    , ACCIONES),
        CALIBRACION_CREAR		(610010, "Crear"			    , A_CALIBRACION),
        CALIBRACION_EDITAR		(610020, "Editar"			    , A_CALIBRACION),
        CALIBRACION_BORRAR		(610030, "Borrar"			    , A_CALIBRACION),
		
    A_CALIFICACION_TIPO			(620000, "Calificación tipos"		    , ACCIONES),
        CALIFICACION_TIPO_CREAR		(620010, "Crear"			    , A_CALIFICACION_TIPO),
        CALIFICACION_TIPO_EDITAR		(620020, "Editar"			    , A_CALIFICACION_TIPO),
        CALIFICACION_TIPO_BORRAR		(620030, "Borrar"			    , A_CALIFICACION_TIPO),

    A_CAPACITACION			(630000, "Capacitación"			    , ACCIONES),
        CAPACITACION_CREAR		(630010, "Crear"			    , A_CAPACITACION),
        CAPACITACION_EDITAR		(630020, "Editar"			    , A_CAPACITACION),
        CAPACITACION_BORRAR		(630030, "Borrar"			    , A_CAPACITACION),

    A_CERTIFICADO			(640000, "Certificados de calidad"	    , ACCIONES),
        CERTIFICADO_CREAR		(640010, "Crear"			    , A_CERTIFICADO),
        CERTIFICADO_EDITAR		(640020, "Editar"			    , A_CERTIFICADO),
        CERTIFICADO_BORRAR		(640030, "Borrar"			    , A_CERTIFICADO),

    A_CHEQUE                		(645000, "Cheques"                          , ACCIONES),
        CHEQUE_CREAR                    (645010, "Crear"                            , A_CHEQUE),
        CHEQUE_EDITAR                   (645020, "Editar"			    , A_CHEQUE),
        CHEQUE_BORRAR                   (645030, "Borrar"			    , A_CHEQUE),

    A_CONTABLE                     	(645500, "Contables"                         , ACCIONES),
        CONTABLE_MODIFICAR_BLOQUEADA    (645510, "Modificar si está bloqueada"       , A_CONTABLE),

        
    A_CUENTAMAESTRO               	(646000, "Cuentas - Ejercicios"         , ACCIONES),
        CUENTAMAESTRO_CREAR             (646010, "Crear"                        , A_CUENTAMAESTRO),
        CUENTAMAESTRO_EDITAR            (646020, "Editar"			, A_CUENTAMAESTRO),
        CUENTAMAESTRO_BORRAR            (646030, "Borrar"                       , A_CUENTAMAESTRO),

    A_CUENTA                            (647000, "Cuentas"                      , ACCIONES),
        CUENTA_CREAR                    (647010, "Crear"                        , A_CUENTA),
        CUENTA_EDITAR                   (647020, "Editar"			, A_CUENTA),
        CUENTA_BORRAR                   (647030, "Borrar"                       , A_CUENTA),

    A_CUENTA_ASIENTO                    (648000, "Cuentas - Asientos"           , ACCIONES),
        CUENTA_ASIENTO_CREAR            (648010, "Crear"                        , A_CUENTA_ASIENTO),
        CUENTA_ASIENTO_EDITAR           (648020, "Editar"			, A_CUENTA_ASIENTO),
        CUENTA_ASIENTO_BORRAR           (648030, "Borrar"                       , A_CUENTA_ASIENTO),

        
    A_COSTEO				(650000, "Costos: parámetros"		, ACCIONES),
        COSTEO_CREAR			(650010, "Crear"			, A_COSTEO),
        COSTEO_EDITAR			(650020, "Editar"			, A_COSTEO),
        COSTEO_BORRAR			(650030, "Borrar"			, A_COSTEO),

    A_COSTO				(660000, "Costos"			, ACCIONES),
        COSTO_CREAR			(660010, "Crear"			, A_COSTO),
        COSTO_EDITAR			(660020, "Editar"			, A_COSTO),
        COSTO_BORRAR			(660030, "Borrar"			, A_COSTO),

    A_DEPARTAMENTO			(665000, "Departamentos, funciones y sus requisitos", ACCIONES),
        DEPARTAMENTO_CREAR		(665010, "Crear"			, A_DEPARTAMENTO),
        DEPARTAMENTO_EDITAR		(665020, "Editar"			, A_DEPARTAMENTO),
        DEPARTAMENTO_BORRAR		(665030, "Borrar"			, A_DEPARTAMENTO),

    A_DOCUMENTO				(670000, "Documentos"			, ACCIONES),
        DOCUMENTO_CREAR			(670010, "Crear"			, A_DOCUMENTO),
        DOCUMENTO_EDITAR			(670020, "Editar"			, A_DOCUMENTO),
        DOCUMENTO_BORRAR			(670030, "Borrar"			, A_DOCUMENTO),

    A_DOCUMENTO_CARPETA			(680000, "Documentos: carpetas"		, ACCIONES),
	DOCUMENTO_CARPETA_CREAR		(680010, "Crear"			, A_DOCUMENTO_CARPETA),
	DOCUMENTO_CARPETA_EDITAR		(680020, "Editar"			, A_DOCUMENTO_CARPETA),
        DOCUMENTO_CARPETA_BORRAR		(680030, "Borrar"			, A_DOCUMENTO_CARPETA),

    A_EMPRESA				(690000, "Empresa"			, ACCIONES),
	EMPRESA_EDITAR			(690020, "Editar"			, A_EMPRESA),

    A_ENTREGA				(700000, "Entregas"			, ACCIONES),
        ENTREGA_CREAR			(700010, "Crear"			, A_ENTREGA),
	ENTREGA_EDITAR			(700010, "Editar"			, A_ENTREGA),
        ENTREGA_BORRAR			(700020, "Borrar"			, A_ENTREGA),
	ENTREGA_ACTUALZIAR		(700030, "Actualizar"			, A_ENTREGA),

    A_EQUIPO				(710000, "Equipos productivos"		, ACCIONES),
        EQUIPO_CREAR			(710010, "Crear"			, A_EQUIPO),
	EQUIPO_EDITAR			(710020, "Editar"			, A_EQUIPO),
        EQUIPO_BORRAR			(710030, "Borrar"			, A_EQUIPO),

    A_EQUIPO_MANTENIMIENTO_1Y2		(720000, "Equipos: Mantenimiento nivel 1 y 2", ACCIONES),
	A_EQUIPO_MANTENIMIENTO_1Y2_CREAR(720010, "Crear"			, A_EQUIPO_MANTENIMIENTO_1Y2),
	A_EQUIPO_MANTENIMIENTO_1Y2_EDITAR(720020, "Editar"			, A_EQUIPO_MANTENIMIENTO_1Y2),
        A_EQUIPO_MANTENIMIENTO_1Y2_BORRAR(720030, "Borrar"			, A_EQUIPO_MANTENIMIENTO_1Y2),

    A_EQUIPO_MANTENIMIENTO_3		(730000, "Equipos: Mantenimiento nivel 3", ACCIONES),
	A_EQUIPO_MANTENIMIENTO_3_CREAR	(730010, "Crear"			, A_EQUIPO_MANTENIMIENTO_3),
	A_EQUIPO_MANTENIMIENTO_3_EDITAR	(730020, "Editar"			, A_EQUIPO_MANTENIMIENTO_3),
        A_EQUIPO_MANTENIMIENTO_3_BORRAR	(730030, "Borrar"			, A_EQUIPO_MANTENIMIENTO_3),

    A_EQUIPO_PLAN_PRODUCCION		(740000, "Equipos: planificación de la producción", ACCIONES),
	EQUIPO_PLAN_PRODUCCION_CREAR	(740010, "Crear"			, A_EQUIPO_PLAN_PRODUCCION),
	EQUIPO_PLAN_PRODUCCION_EDITAR	(740020, "Editar"			, A_EQUIPO_PLAN_PRODUCCION),
        EQUIPO_PLAN_PRODUCCION_BORRAR	(740030, "Borrar"			, A_EQUIPO_PLAN_PRODUCCION),

    A_EXTERNO				(750000, "Entes externos"		, ACCIONES),
        EXTERNO_CREAR			(750010, "Crear"			, A_EXTERNO),
        EXTERNO_EDITAR			(750020, "Editar"			, A_EXTERNO),
        EXTERNO_BORRAR			(750030, "Borrar"			, A_EXTERNO),

    A_EXTERNO_ARTICULO			(760000, "Entes externos: asignar artículos", ACCIONES),
        EXTERNO_ARTICULO_CREAR		(760010, "Crear"			, A_EXTERNO_ARTICULO),
        EXTERNO_ARTICULO_EDITAR		(760020, "Editar"			, A_EXTERNO_ARTICULO),
        EXTERNO_ARTICULO_BORRAR		(760030, "Borrar"			, A_EXTERNO_ARTICULO),

    A_EXTERNO_CLASE			(770000, "Entes externos: clases"	, ACCIONES),
        EXTERNO_CLASE_CREAR		(770010, "Crear"			, A_EXTERNO_CLASE),
        EXTERNO_CLASE_EDITAR		(770020, "Editar"			, A_EXTERNO_CLASE),
        EXTERNO_CLASE_BORRAR		(770030, "Borrar"			, A_EXTERNO_CLASE),

    A_EXTERNO_EMBALAJE			(780000, "Entes externos: embalajes"	, ACCIONES),
        EXTERNO_EMBALAJE_CREAR		(780010, "Crear"			, A_EXTERNO_EMBALAJE),
        EXTERNO_EMBALAJE_EDITAR		(780020, "Editar"			, A_EXTERNO_EMBALAJE),
        EXTERNO_EMBALAJE_BORRAR		(780030, "Borrar"			, A_EXTERNO_EMBALAJE),

    A_EXTERNO_PUERTO			(790000, "Entes externos: puertos"	, ACCIONES),
        EXTERNO_PUERTO_CREAR		(790010, "Crear"			, A_EXTERNO_PUERTO),
        EXTERNO_PUERTO_EDITAR		(790020, "Editar"			, A_EXTERNO_PUERTO),
        EXTERNO_PUERTO_BORRAR		(790030, "Borrar"			, A_EXTERNO_PUERTO),

    A_FALLA	    			(800000, "Fallas de calidad"		, ACCIONES),
        FALLA_CREAR			(800010, "Crear"			, A_FALLA),
        FALLA_EDITAR			(800020, "Editar"			, A_FALLA),
        FALLA_BORRAR			(800030, "Borrar"			, A_FALLA),

    A_FALLA_TIPO	    		(805000, "Fallas de calidad: tipos"	, ACCIONES),
        FALLA_TIPO_CREAR			(805010, "Crear"			, A_FALLA_TIPO),
        FALLA_TIPO_EDITAR		(805020, "Editar"			, A_FALLA_TIPO),
        FALLA_TIPO_BORRAR		(805030, "Borrar"			, A_FALLA_TIPO),
	
    A_FRECUENCIA	    			(810000, "Frecuencias de control"	,ACCIONES),
        FRECUENCIA_CREAR			(810010, "Crear"				, A_FRECUENCIA),
        FRECUENCIA_EDITAR		(810020, "Editar"			, A_FRECUENCIA),
        FRECUENCIA_BORRAR		(810030, "Borrar"			, A_FRECUENCIA),
	
    A_ICPM	    			(820000, "Icpm"				,ACCIONES),
        ICPM_CREAR			(820010, "Crear"			, A_ICPM),
        ICPM_EDITAR			(820020, "Editar"			, A_ICPM),
        ICPM_BORRAR			(820030, "Borrar"			, A_ICPM),

    A_INSTRUMENTO	 		(830000, "Instrumentos de medición"	,ACCIONES),
        INSTRUMENTO_CREAR		(830010, "Crear"			, A_INSTRUMENTO),
        INSTRUMENTO_EDITAR		(830020, "Editar"			, A_INSTRUMENTO),
        INSTRUMENTO_BORRAR		(830030, "Borrar"			, A_INSTRUMENTO),

    A_INSTRUMENTO_TIPO	 		(840000, "Instrumentos de medición: tipos"  ,ACCIONES),
        INSTRUMENTO_TIPO_CREAR		(840010, "Crear"			    , A_INSTRUMENTO_TIPO),
        INSTRUMENTO_TIPO_EDITAR		(840020, "Editar"			    , A_INSTRUMENTO_TIPO),
        INSTRUMENTO_TIPO_BORRAR		(840030, "Borrar"			    , A_INSTRUMENTO_TIPO),

    A_LOG				(850000, "Log: registro de eventos"	    ,ACCIONES),
        LOG_CREAR			(850010, "Crear"			    , A_LOG),
        LOG_EDITAR			(850020, "Editar"			    , A_LOG),
        LOG_BORRAR			(850030, "Borrar"			    , A_LOG),
	LOG_BORRAR_TODOS			(850040, "Borrar todo el historial"	    , A_LOG),

    A_PROCESO_TIPO			(855000, "Procesos: tipos"		, ACCIONES),
        PROCESO_TIPO_CREAR		(855010, "Crear"			, A_PROCESO_TIPO),
        PROCESO_TIPO_EDITAR		(855020, "Editar"			, A_PROCESO_TIPO),
        PROCESO_TIPO_BORRAR		(855030, "Borrar"			, A_PROCESO_TIPO),

    A_LIBERACION			(857000, "Liberaciones"		    	, ACCIONES),
        LIBERACION_CREAR			(857010, "Crear"			, A_LIBERACION),
        LIBERACION_EDITAR		(857020, "Editar"			, A_LIBERACION),
        LIBERACION_BORRAR		(857030, "Borrar"			, A_LIBERACION),
        LIBERACION_EDITAR_CANT_DIAS	(857050, "Editar cantidad dias"         , A_LIBERACION),
        LIBERACION_EDITAR_FECHA     	(857070, "Editar fecha"                 , A_LIBERACION),
	
    A_LOTE				(860000, "Partidas"			, ACCIONES),
        LOTE_CREAR			(860010, "Crear"			, A_LOTE),
        LOTE_EDITAR			(860020, "Editar"			, A_LOTE),
        LOTE_BORRAR			(860030, "Borrar"			, A_LOTE),

    A_LOTE_PROCESO			(870000, "Partidas: procesos"		, ACCIONES),
        LOTE_PROCESO_CREAR		(870010, "Crear"			, A_LOTE_PROCESO),
        LOTE_PROCESO_EDITAR		(870020, "Editar"			, A_LOTE_PROCESO),
        LOTE_PROCESO_BORRAR		(870030, "Borrar"			, A_LOTE_PROCESO),
	LOTE_PROCESO_IMPRIMIR		(870040, "Imprimir"			, A_LOTE_PROCESO),
	LOTE_PROCESO_MEDI_AUTO		(870070, "Crear mediciones AUTO"	, A_LOTE_PROCESO),

    A_LOTE_COTA		    		(880000, "Partidas: características"	, ACCIONES),
        LOTE_COTA_CREAR			(880010, "Crear"			, A_LOTE_COTA),
        LOTE_COTA_EDITAR			(880020, "Editar"			, A_LOTE_COTA),
        LOTE_COTA_BORRAR			(880030, "Borrar"			, A_LOTE_COTA),

    A_LOTE_MEDIDA		    	(890000, "Partidas: medidas"		, ACCIONES),
        LOTE_MEDIDA_CREAR		(890010, "Crear"			, A_LOTE_MEDIDA),
        LOTE_MEDIDA_EDITAR		(890020, "Editar"			, A_LOTE_MEDIDA),
        LOTE_MEDIDA_BORRAR		(890030, "Borrar"			, A_LOTE_MEDIDA),

    A_LOTE_COMPONENTE			(900000, "Partidas: componentes"	, ACCIONES),
        LOTE_COMPONENTE_CREAR		(900010, "Crear"			, A_LOTE_COMPONENTE),
        LOTE_COMPONENTE_EDITAR		(900020, "Editar"			, A_LOTE_COMPONENTE),
        LOTE_COMPONENTE_BORRAR		(900030, "Borrar"			, A_LOTE_COMPONENTE),

   A_LOTE_COMPONENTE_ASIG		(910000, "Partidas: partidas de componentes", ACCIONES),
        LOTE_COMPONENTE_ASIG_CREAR	(910010, "Crear"			, A_LOTE_COMPONENTE_ASIG),
        LOTE_COMPONENTE_ASIG_EDITAR	(910020, "Editar"			, A_LOTE_COMPONENTE_ASIG),
        LOTE_COMPONENTE_ASIG_BORRAR	(910030, "Borrar"			, A_LOTE_COMPONENTE_ASIG),

    A_MAGNITUD		    		(920000, "Magnitudes"			, ACCIONES),
        MAGNITUD_CREAR			(920010, "Crear"			, A_MAGNITUD),
        MAGNITUD_EDITAR			(920020, "Editar"			, A_MAGNITUD),
        MAGNITUD_BORRAR			(920030, "Borrar"			, A_MAGNITUD),

    A_MATERIAL		    		(930000, "Materiales de ingeniería"	, ACCIONES),
        MATERIAL_CREAR			(930010, "Crear"			, A_MATERIAL),
        MATERIAL_EDITAR			(930020, "Editar"			, A_MATERIAL),
        MATERIAL_BORRAR			(930030, "Borrar"			, A_MATERIAL),

    A_MEJORA		    		(935000, "Mejoras"              	, ACCIONES),
        MEJORA_CREAR			(935010, "Crear"			, A_MEJORA),
        MEJORA_EDITAR			(935020, "Editar"			, A_MEJORA),
        MEJORA_BORRAR			(935030, "Borrar"			, A_MEJORA),

    A_MONEDA		    		(940000, "Monedas"			, ACCIONES),
        MONEDA_CREAR			(940010, "Crear"			, A_MONEDA),
        MONEDA_EDITAR			(940020, "Editar"			, A_MONEDA),
        MONEDA_BORRAR			(940030, "Borrar"			, A_MONEDA),
	
    A_MONITOR      			(945000, "Monitores"			, ACCIONES),
        MONITOR_CREAR			(945010, "Crear"			, A_MONITOR),
        MONITOR_EDITAR			(945020, "Editar"			, A_MONITOR),
        MONITOR_BORRAR			(945030, "Borrar"			, A_MONITOR),
        MONITOR_PROCESO_CREAR		(945040, "Crear asignacion de proceso"	, A_MONITOR),
        MONITOR_PROCESO_EDITAR		(945050, "Modificar asignación de proceso", A_MONITOR),
        MONITOR_PROCESO_BORRAR		(945060, "Borrar asignación de proceso" , A_MONITOR),
        
	
    A_NOTA		    		(950000, "Notas"			, ACCIONES),
        NOTA_CREAR			(950010, "Crear"			, A_NOTA),
        NOTA_EDITAR			(950020, "Editar"			, A_NOTA),
        NOTA_BORRAR			(950030, "Borrar"			, A_NOTA),

    A_ORDEN_DE_COMPRA	    		(960000, "Ordenes de compra"		, ACCIONES),
        ORDEN_DE_COMPRA_CREAR		(960010, "Crear"			, A_ORDEN_DE_COMPRA),
        ORDEN_DE_COMPRA_EDITA		(960020, "Editar"			, A_ORDEN_DE_COMPRA),
        ORDEN_DE_COMPRA_BORRAR		(960030, "Borrar"			, A_ORDEN_DE_COMPRA),

    A_PEDIDO	    	    		(970000, "Pedidos"			, ACCIONES),
        PEDIDO_CREAR			(970010, "Crear"			, A_PEDIDO),
        PEDIDO_EDITA			(970020, "Editar"			, A_PEDIDO),
        PEDIDO_BORRAR			(970030, "Borrar"			, A_PEDIDO),
	
    A_PLAN				(980000, "Planificación de calidad"	, ACCIONES),
        PLAN_CREAR			(980010, "Crear"			, A_PLAN),
        PLAN_EDITAR			(980020, "Editar"			, A_PLAN),
        PLAN_BORRAR			(980030, "Borrar"			, A_PLAN),
	PLAN_EXPORTAR			(980040, "Exportar"			, A_PLAN),

    A_PLAN_PROCESO			(990000, "Planificación de calidad: procesos", ACCIONES),
        PLAN_PROCESO_CREAR		(990010, "Crear"			, A_PLAN_PROCESO),
        PLAN_PROCESO_EDITAR		(990020, "Editar"			, A_PLAN_PROCESO),
        PLAN_PROCESO_BORRAR		(990030, "Borrar"			, A_PLAN_PROCESO),

    A_PLAN_COTA		    		(1000000, "Planificación de calidad: características"	, ACCIONES),
        PLAN_COTA_CREAR			(1000010, "Crear"			, A_PLAN_COTA),
        PLAN_COTA_EDITAR			(1000020, "Editar"			, A_PLAN_COTA),
        PLAN_COTA_BORRAR			(1000030, "Borrar"			, A_PLAN_COTA),

    A_PLAN_COMPONENTE			(1010000, "Planificación de calidad: componentes"	, ACCIONES),
        PLAN_COMPONENTE_CREAR		(1010010, "Crear"			, A_PLAN_COMPONENTE),
        PLAN_COMPONENTE_EDITAR		(1010020, "Editar"			, A_PLAN_COMPONENTE),
        PLAN_COMPONENTE_BORRAR		(1010030, "Borrar"			, A_PLAN_COMPONENTE),

    A_REMITO				(1020000, "Remitos"		    	, ACCIONES),
        REMITO_CREAR			(1020010, "Crear"			, A_REMITO),
        REMITO_EDITAR			(1020020, "Editar"			, A_REMITO),
        REMITO_BORRAR			(1020030, "Borrar"			, A_REMITO),
        REMITO_IMPRIMIR			(1020040, "Imprimir"			, A_REMITO),

    A_RETENCION_GNC			(1030000, "Retenciones de ganancias"   	, ACCIONES),
        RETENCION_GNC_CREAR		(1030010, "Crear"			, A_RETENCION_GNC),
        RETENCION_GNC_EDITAR		(1030020, "Editar"			, A_RETENCION_GNC),
        RETENCION_GNC_BORRAR		(1030030, "Borrar"			, A_RETENCION_GNC),
        RETENCION_GNC_IMPRIMIR		(1030040, "Imprimir"			, A_RETENCION_GNC),

    A_RETENCION_IIBB			(1035000, "Retenciones de II.BB."   	, ACCIONES),
        RETENCION_IIBB_CREAR		(1035010, "Crear"			, A_RETENCION_IIBB),
        RETENCION_IIBB_EDITAR		(1035020, "Editar"			, A_RETENCION_IIBB),
        RETENCION_IIBB_BORRAR		(1035030, "Borrar"			, A_RETENCION_IIBB),
        RETENCION_IIBB_IMPRIMIR		(1035040, "Imprimir"			, A_RETENCION_IIBB),
	
    A_ROSCA	    			(1040000, "Roscas de ingeniería"   	, ACCIONES),
        ROSCA_CREAR			(1040010, "Crear"			, A_ROSCA),
        ROSCA_EDITAR			(1040020, "Editar"			, A_ROSCA),
        ROSCA_BORRAR			(1040030, "Borrar"			, A_ROSCA),

    A_RyR	    			(1050000, "RyR"			      	, ACCIONES),
        RyR_CREAR			(1050010, "Crear"			, A_RyR),
        RyR_EDITAR			(1050020, "Editar"			, A_RyR),
        RyR_BORRAR			(1050030, "Borrar"			, A_RyR),

    A_STOCK_ALMACEN	    		(1060000, "Stock: almacenes"		, ACCIONES),
        STOCK_ALMACEN_CREAR		(1060010, "Crear"			, A_STOCK_ALMACEN),
        STOCK_ALMACEN_EDITAR		(1060020, "Editar"			, A_STOCK_ALMACEN),
        STOCK_ALMACEN_BORRAR		(1060030, "Borrar"			, A_STOCK_ALMACEN),
        STOCK_ALMACEN_EXPORTAR_SIN_PRECIO(1060040, "Exportar SIN precios"	, A_STOCK_ALMACEN),
        STOCK_ALMACEN_EXPORTAR_CON_PRECIO(1060060, "Exportar CON precios"	, A_STOCK_ALMACEN),

    A_STOCK_UBICACION	    		(1070000, "Stock: ubicaciones"		, ACCIONES),
        STOCK_UBICACION_CREAR		(1070010, "Crear"			, A_STOCK_UBICACION),
        STOCK_UBICACION_EDITAR		(1070020, "Editar"			, A_STOCK_UBICACION),
        STOCK_UBICACION_BORRAR		(1070030, "Borrar"			, A_STOCK_UBICACION),

    A_SUELDO		    		(1080000, "Recibos de sueldo"		, ACCIONES),
        SUELDO_CREAR			(1080010, "Crear"			, A_SUELDO),
        SUELDO_EDITAR			(1080020, "Editar"			, A_SUELDO),
        SUELDO_BORRAR			(1080030, "Borrar"			, A_SUELDO),

    A_SUELDO_DEF		    	(1090000, "Recibos de sueldo: definición", ACCIONES),
        SUELDO_DEF_CREAR			(1090010, "Crear"			, A_SUELDO_DEF),
        SUELDO_DEF_EDITAR		(1090020, "Editar"			, A_SUELDO_DEF),
        SUELDO_DEF_BORRAR		(1090030, "Borrar"			, A_SUELDO_DEF),
	
    A_SUGERENCIA				(1100000, "Sugerencias del personal"	, ACCIONES),
        SUGERENCIA_CREAR			(1100010, "Crear"			, A_SUGERENCIA),
        SUGERENCIA_EDITAR		(1100020, "Editar"			, A_SUGERENCIA),
        SUGERENCIA_BORRAR		(1100030, "Borrar"			, A_SUGERENCIA),
	
    A_TERMINAL				(1110000, "Terminales"			, ACCIONES),
        TERMINAL_CREAR			(1110010, "Crear"			, A_TERMINAL),
        TERMINAL_EDITAR			(1110020, "Editar"			, A_TERMINAL),
        TERMINAL_BORRAR			(1110030, "Borrar"			, A_TERMINAL),

    A_USUARIO				(1120000, "Usuarios"			, ACCIONES),
        USUARIO_CREAR			(1120010, "Crear"			, A_USUARIO),
        USUARIO_EDITAR			(1120020, "Editar"			, A_USUARIO),
        USUARIO_BORRAR			(1120030, "Borrar"			, A_USUARIO),
        USUARIO_CALCULAR_ANTIGUEDAD	(1120040, "Calcular antiguedad"		, A_USUARIO),

    A_USUARIO_PERMISO			(1130000, "Usuarios: permisos"		, ACCIONES),
        USUARIO_PERMISO_CREAR		(1130010, "Crear"			, A_USUARIO_PERMISO),
        USUARIO_PERMISO_EDITAR		(1130020, "Editar"			, A_USUARIO_PERMISO),
        USUARIO_PERMISO_BORRAR		(1130030, "Borrar"			, A_USUARIO_PERMISO),
	
    A_USUARIO_ROL			(1140000, "Usuarios: Roles"		, ACCIONES),
        USUARIO_ROL_CREAR		(1140010, "Crear"			, A_USUARIO_ROL),
        USUARIO_ROL_EDITAR		(1140020, "Editar"			, A_USUARIO_ROL),
        USUARIO_ROL_BORRAR		(1140030, "Borrar"			, A_USUARIO_ROL),

    A_VISITA				(1150000, "Visitas"			, ACCIONES),
        VISITA_CREAR			(1150010, "Crear"			, A_VISITA),
        VISITA_EDITAR		        (1150020, "Editar"			, A_VISITA),
        VISITA_BORRAR			(1150030, "Borrar"			, A_VISITA),

    A_SISTEMA_M                         (1200000, "Sistema M"			, ACCIONES),
        A_SISTEMA_M_CREAR_LOTE		(1200010, "Crear lote"			, A_SISTEMA_M);

    
    private final Integer id;
    private final String nombre;
    private final EnumPermiso padre;
    private final int nivel; //0: visualización    1: Acciones   2: otros

    private EnumPermiso(Integer id, String nombre, EnumPermiso padre, int nivel) {

        this.id = id;
        this.nombre = nombre;
        this.padre = padre;
        this.nivel = nivel;
    }

    private EnumPermiso(Integer id, String nombre, EnumPermiso padre) {

        this.id = id;
        this.nombre = nombre;
        this.padre = padre;
        this.nivel = padre.nivel;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EnumPermiso getPadre() {
        return padre;
    }

    public int getNivel() {
        return nivel;
    }

    

    @Override
    public String toString() {
        return nombre;
    }
}
