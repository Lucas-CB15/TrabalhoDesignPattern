public class Main {

    // 1. Factory Method (Criacional)
    abstract static class Documento {
        public abstract void print();
    }

    static class TXT extends Documento {
        @Override
        public void print() {
            System.out.println("Imprimindo documento TXT...");
        }
    }

    static class HTML extends Documento {
        @Override
        public void print() {
            System.out.println("Imprimindo documento HTML...");
        }
    }

    abstract static class DocumentoFactory {
        public abstract Documento createDocument();
    }

    static class TXTFactory extends DocumentFactory {
        @Override
        public Documento createDocument() {
            return new Report();
        }
    }

    static class HTMLFactory extends DocumentFactory {
        @Override
        public Documento createDocument() {
            return new Memo();
        }
    }

    // 2. Adapter (Estrutural)
    interface DocumentoImpresso {
        void printDocument();
    }

    static class DocumentoPDF {
        public void printPDF() {
            System.out.println("Imprimindo documento PDF...");
        }
    }

    static class DocumentoDOCX {
        public void printDOCX() {
            System.out.println("Iprimindo documento DOCX...");
        }
    }

    static class PDFAdapter implements DocumentoImpresso {
        private DocumentoPDF documentoPDF;

        public PDFAdapter(DocumentoPDF documentoPDF) {
            this.documentoPDF = documentoPDF;
        }

        @Override
        public void printDocument() {
            documentoPDF.printPDF();
        }
    }

    static class DOCXAdapter implements DocumentoImpresso {
        private DocumentoDOCX documentoDOCX;

        public DOCXAdapter(DocumentoDOCX documentoDOCX) {
            this.documentoDOCX = documentoDOCX;
        }

        @Override
        public void printDocument() {
            documentoDOCX.printDOCX();
        }
    }

    // 3. Observer (Comportamental)
    interface DocumentoObserver {
        void update(String mensagem);
    }

    static class Usuario implements DocumentoObserver {
        private String nome;

        public User(String nome) {
            this.nome = nome;
        }

        @Override
        public void update(String mensagem) {
            System.out.println("Usuario " + name + ": " + mensagem);
        }
    }

    static class DocumentSubject {
        private List<DocumentoObserver> observers = new ArrayList<>();
        private String conteudo;

        public void addObserver(DocumentoObserver observer) {
            observers.add(observer);
        }

        public void removeObserver(DocumentoObserver observer) {
            observers.remove(observer);
        }

        public void setContent(String conteudo) {
            this.conteudo = conteudo;
            notifyObservers();
        }

        private void notifyObservers() {
            for (DocumentoObserver observer : observers) {
                observer.update("O conteúdo do documento foi atualizado para: " + conteudo);
            }
        }
    }

    public static void main(String[] args) {
        // 1. Teste Factory Method
        DocumentoFactory txtFactory = new TXTFactory();
        Documento txt = txtFactory.createDocument();
        txt.print();

        DocumentoFactory htmlFactory = new HTMLFactory();
        Documento html = htmlFactory.createDocument();
        html.print();

        // 2. Teste Adapter
        DocumentoImpresso pdfAdapter = new PDFAdapter(new DocumentoPDF());
        pdfAdapter.printDocument();

        DocumentoImpresso docxAdapter = new DOCXAdapter(new DocumentoDOCX());
        docxAdapter.printDocument();

        // 3. Teste Observer
        DocumentSubject documento = new DocumentSubject();
        usuario usuario1 = new Usuario("Lucas");
        usuario usuario2 = new Usuario("Calleri");

        documento.addObserver(usuario1);
        documento.addObserver(usuario2);

        document.setContent("Conteúdo novo para o documento");
    }
}