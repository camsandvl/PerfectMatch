💄 Perfect Match
Descripción del Proyecto
Perfect Match es una aplicación web que recomienda productos de maquillaje personalizados (blush, corrector y rímel) a los usuarios, utilizando sus preferencias y retroalimentación.
El sistema aprende de las respuestas y gustos de cada usuario para sugerir productos similares y mejorar la experiencia de recomendación.

🎯 Problemática
En el mercado de maquillaje existe una enorme variedad de productos, lo que dificulta a los usuarios encontrar aquellos que realmente se adaptan a sus gustos, necesidades y características personales.
Muchas veces, las recomendaciones genéricas no consideran preferencias individuales, lo que lleva a compras insatisfactorias.

✅ Objetivo
Desarrollar una plataforma inteligente que:

Recomiende productos de maquillaje personalizados según las respuestas de un quiz y la retroalimentación del usuario.

Aprenda de los gustos del usuario para sugerir productos similares en el futuro.

Permita a los usuarios dar retroalimentación sobre los productos recomendados para mejorar la precisión de las sugerencias.

🧠 Modelo de Datos
🔗 Nodos Principales
Usuario: Representa a cada persona que utiliza la plataforma.

Blush: Productos de rubor.

Concelear: Productos de corrector.

Rímel: Productos de rímel/máscara de pestañas.

Respuestas: Nodo auxiliar que almacena las respuestas del quiz de cada usuario.

🔄 Relaciones entre Nodos
scss
Copiar
Editar
(Usuario)-[:RESPONDIO]->(Respuestas)  
(Usuario)-[:PREFIERE_BLUSH]->(Blush)  
(Usuario)-[:PREFIERE_CONCELEAR]->(Concelear)  
(Usuario)-[:PREFIERE_RIMEL]->(Rimel)  
(Usuario)-[:RETROALIMENTO {gusto: true/false, fecha}]->(Producto)  
(Blush)-[:SIMILAR_BLUSH]->(Blush)  
(Concelear)-[:SIMILAR_CONCELEAR]->(Concelear)  
(Rimel)-[:SIMILAR_RIMEL]->(Rimel)  
🛠 Tecnologías Usadas
Java 17 (Spring Boot)

Neo4j (Base de datos de grafos)

Thymeleaf (Plantillas HTML)

HTML5, CSS3, JavaScript (Frontend)

Maven (Gestión de dependencias)

Neo4j Browser (Visualización y administración de la base de datos)

💻 Requerimientos de Software
Java 17 o superior

Maven 3.6+

Neo4j 5.x (servidor local o remoto)

Node.js (opcional, solo si deseas usar herramientas de frontend modernas)

🚀 Instalación y Ejecución
Clona el repositorio:

bash
Copiar
Editar
git clone https://github.com/tuusuario/perfect-match.git
cd perfect-match
Configura Neo4j:

Instala Neo4j Desktop o Neo4j Community Edition.

Crea una base de datos.

Actualiza el archivo application.properties con tu usuario, contraseña y puerto de Neo4j.

Compila y ejecuta el backend:

arduino
Copiar
Editar
mvn spring-boot:run
Accede a la aplicación:

Abre tu navegador en http://localhost:8080

📝 Notas
El sistema carga productos de ejemplo automáticamente al iniciar.

Puedes visualizar y modificar los nodos y relaciones en Neo4j Browser.

El frontend es completamente responsive y amigable para el usuario.

👩‍💻 Autoras
Alejandra Sierra

Camila Sandoval

Emily Góngora

✨ ¿Listo para encontrar tu Perfect Match en maquillaje?
¡Explora tu estilo con recomendaciones personalizadas! 💖
