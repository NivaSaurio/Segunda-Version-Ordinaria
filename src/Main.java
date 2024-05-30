import java.util.ArrayList;
import java.util.List;

// Clase Autor
/*
 * Crea un autor con sus atributos
 */
class Autor {
    private String nombre;

    public Autor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase Libros
/*
 * Crea un libro con sus atributos
 * Se controla las excepciones que puedan darse al establecer el número de páginas o el número de carácteres de la sinopsis
 * En esta versión se ha modificado el mínimo número de carácteres a 10 ya que con 100 saltaba excepcion al no cumplirse el mínimo de carácteres
 * Se sobreescribe toString() para representar el libro 
 */
class Libro {
    private String titulo;
    private Autor autor;
    private String editorial;
    private int numeroPaginas;
    private String sinopsis;
    private boolean prestado;

    public Libro(String titulo, Autor autor, String editorial, int numeroPaginas, String sinopsis, boolean prestado) {
        if (numeroPaginas <= 100) {
            throw new IllegalArgumentException("El libro debe tener más de 100 páginas.");
        }
        if (sinopsis.length() < 10 || sinopsis.length() > 500) {
            throw new IllegalArgumentException("La sinopsis debe tener entre 10 y 500 caracteres.");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.sinopsis = sinopsis;
        this.prestado = prestado;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor.getNombre() + ", Editorial: " + editorial + 
               ", Páginas: " + numeroPaginas + ", Sinopsis: " + sinopsis + ", Prestado: " + prestado;
    }
}

// Clase Biblioteca
/*
 * Se crea una biblioteca compuesta con ArrayList de autor y libros
 * bucarAutor() itera sobre el ArrayList de autor para encontrar el autor con el nombre pasado como parámetro
 * agregarAutor(), agregarLibro(), usan el método add. para agregar autores y libros
 * mostrarLibros() muestra los libros del arrayList
 */
class Biblioteca {
    private List<Autor> autores;
    private List<Libro> libros;

    public Biblioteca() {
        autores = new ArrayList<>();
        libros = new ArrayList<>();
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public Autor buscarAutor(String nombre) {
        for (Autor autor : autores) {
            if (autor.getNombre().equalsIgnoreCase(nombre)) {
                return autor;
            }
        }
        return null;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void mostrarLibros() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}

// Clase principal
/*
 * Se crea una biblioteca con autores, estos autores tienen libros los cuales cumplen correctamente con los atributos
 * Se muestran en pantalla los libros
 */
public class Main {
    public static void main(String[] args) {
        // Crear biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Agregar autores
        Autor autor1 = new Autor("Gabriel García Márquez");
        Autor autor2 = new Autor("Isabel Allende");
        biblioteca.agregarAutor(autor1);
        biblioteca.agregarAutor(autor2);

        // Crear y agregar libros
        try {
            Libro libro1 = new Libro("Cien años de soledad", autor1, "Sudamericana", 417, "Una obra magistral sobre la familia Buendía.", false);
            Libro libro2 = new Libro("La casa de los espíritus", autor2, "Debolsillo", 433, "Un clásico de la literatura latinoamericana.", true);
            biblioteca.agregarLibro(libro1);
            biblioteca.agregarLibro(libro2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Mostrar libros
        biblioteca.mostrarLibros();
    }
}
