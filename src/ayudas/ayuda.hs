<?xml version="1.0" encoding="ISO-8859-1"?>
<helpset>
	<title>AYUDA ConectApp</title>
		<maps>
			<homeID>pagOpciones</homeID>		<!-- P�gina por defecto al mostrar la ayuda -->
			<mapref location="ayuda.jhm"/>	<!-- Que mapa deseamos -->
		</maps>

		<!-- Las Vistas que deseamos mostrar en la ayuda -->
		<view>						<!-- Deseamos una tabla de contenidos -->
			<name>tablaContenidos</name>
			<label>Tabla de contenidos</label>	<!-- El tooltiptext -->
			<type>javax.help.TOCView</type>
			<data>ayudaTOC.xml</data>		<!-- El fichero que la define -->
                        <image>imgTOC</image>
		</view>

                <view>						<!-- Deseamos una tabla de contenidos -->
			<name>indice</name>
			<label>Indice</label>	<!-- El tooltiptext -->
			<type>javax.help.IndexView</type>
			<data>ayudaIndice.xml</data>		<!-- El fichero que la define -->
                        <image>imgIndice</image>
		</view>

                <view>						<!-- Deseamos una tabla de contenidos -->
			<name>busqueda</name>
			<label>Busquedas</label>	<!-- El tooltiptext -->
			<type>javax.help.SearchView</type>
			<data engine="com.sun.java.help.search.DefaultSearchEngine">JavaHelpSearch</data>		<!-- El fichero que la define -->
                        <image>imgBusqueda</image>
		</view>
                
                <view>						<!-- Deseamos una tabla de contenidos -->
			<name>favoritos</name>
			<label>Favoritos</label>	<!-- El tooltiptext -->
			<type>javax.help.FavoritesView</type>
                        <image>imgFavoritos</image>
		</view>

		
		<presentation default=true>
			<name>MainWin</name>
			<size width="640" height="480"/>		<!-- Dimensiones iniciales -->
			<location x="200" y="100"/>			<!-- Posici�n inicial -->
                        <image>imgAyuda</image>
			
			<toolbar>	<!-- Definimos la barra de herramientas de la ventana -->
				<!-- Permitimos ir a la p�gina anterior -->
				<helpaction image="imgAnterior">javax.help.BackAction</helpaction>
				<!-- Permitimos ir a la p�gina siguiente -->
				<helpaction image="imgSiguiente">javax.help.ForwardAction</helpaction>
                                <!-- Permitimos agregar a favoritos -->
                                <helpaction image="imgAnhadirFavorito">javax.help.FavoritesAction</helpaction>
			</toolbar>
		</presentation>                



</helpset>
