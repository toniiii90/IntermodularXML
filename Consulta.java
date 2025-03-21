public class Consulta { // Antonio García Clavel
    private String fechaCon;
    private String diagnostico;
    private int id_consulta;

    public Consulta(String fechaCon, String diagnostico, int id_consulta) throws IllegalArgumentException{
        setFechaCon(fechaCon);
        setDiagnostico(diagnostico);
        setId_consulta(id_consulta);
    }

    public String getFechaCon() {
        return fechaCon;
    }

    public void setFechaCon(String fechaCon) {
        if (fechaCon == null || fechaCon.isEmpty()) {
            throw new IllegalArgumentException("la fecha de la consulta no puede estar vacía");
        }
        this.fechaCon = fechaCon;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico no puede estar vacío.");
        }
        this.diagnostico = diagnostico;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        if (id_consulta <= 0) {
            throw new IllegalArgumentException("El ID de la consulta debe ser un número positivo.");
        }
        this.id_consulta = id_consulta;
    }

    @Override
    public String toString() {
        return "<Consulta>\n" +
               "    <fechaCon>" + fechaCon + "</fechaCon>\n" +
               "    <diagnostico>" + diagnostico + "</diagnostico>\n" +
               "    <id_consulta>" + id_consulta + "</id_consulta>\n" +
               "</Consulta>";
    }
}
