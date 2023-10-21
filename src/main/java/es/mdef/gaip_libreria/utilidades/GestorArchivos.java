package es.mdef.gaip_libreria.utilidades;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import es.mdef.gaip.anfitriones.modelos.AnfitrionPostModel;
import es.mdef.gaip.utilidades.CellStyleManager;
import es.mdef.gaip.utilidades.Estilos;
import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.invitados.InvitadoFcse;
import es.mdef.gaip_libreria.localidades.LocalidadNumerada;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Service
public final class GestorArchivos {
    private GestorArchivos() {
    }

    public static Set<AnfitrionPostModel> extraerAnfitriones(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if ("csv".equalsIgnoreCase(extension)) {
            return parseCSV(file);
        } else if ("xlsx".equalsIgnoreCase(extension) || "xls".equalsIgnoreCase(extension)) {
            return parseExcel(file);
        } else {
            throw new IllegalArgumentException("Formato de archivo no soportado");
        }
    }

    public static Workbook exportarDatosInvitadosParaImprenta(Acto acto) {
        Workbook workbook = new XSSFWorkbook();
        CellStyleManager cellStyleManager = new CellStyleManager(workbook);

        Sheet sheet = workbook.createSheet("Invitados al acto - " + acto.getNombre());
        sheet.createFreezePane(0, 3);

        int fila = 0;
        boolean useColorStyle1 = true;
        sheet.createRow(fila++);
        Row headerRow = sheet.createRow(fila++);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 14));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 15, 20));

        Cell cellAlumno = headerRow.createCell(0);
        cellAlumno.setCellValue("Alumno");
        cellAlumno.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));

        Cell cellDatosInvitacion = headerRow.createCell(3);
        cellDatosInvitacion.setCellValue("Datos invitación");
        cellDatosInvitacion.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));

        Cell cellInvitados = headerRow.createCell(6);
        cellInvitados.setCellValue("Invitados");
        cellInvitados.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));

        Cell cellDatosFCSE = headerRow.createCell(15);
        cellDatosFCSE.setCellValue("Datos FCSE");
        cellDatosFCSE.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER2));

        for (int i = 0; i < 21; i++) {
            Cell cell = headerRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (i < 15) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER2));
            }
        }

        Row subHeaderRow = sheet.createRow(fila++);
        subHeaderRow.createCell(0).setCellValue("Empleo");
        subHeaderRow.createCell(1).setCellValue("Nombre");
        subHeaderRow.createCell(2).setCellValue("Sección");
        subHeaderRow.createCell(3).setCellValue("Tipo de invitación");
        subHeaderRow.createCell(4).setCellValue("Tribuna");
        subHeaderRow.createCell(5).setCellValue("Asiento");
        subHeaderRow.createCell(6).setCellValue("DNI");
        subHeaderRow.createCell(7).setCellValue("Nombre");
        subHeaderRow.createCell(8).setCellValue("Primer Apellido");
        subHeaderRow.createCell(9).setCellValue("Segundo Apellido");
        subHeaderRow.createCell(10).setCellValue("Fecha de Nacimiento");
        subHeaderRow.createCell(11).setCellValue("Parentesco");
        subHeaderRow.createCell(12).setCellValue("Matrícula Coche");
        subHeaderRow.createCell(13).setCellValue("Modelo Coche");
        subHeaderRow.createCell(14).setCellValue("Color Coche");
        subHeaderRow.createCell(15).setCellValue("Cuerpo");
        subHeaderRow.createCell(16).setCellValue("Empleo");
        subHeaderRow.createCell(17).setCellValue("Situación");
        subHeaderRow.createCell(18).setCellValue("Asiste de uniforme");
        subHeaderRow.createCell(19).setCellValue("Requiere vestuario");
        subHeaderRow.createCell(20).setCellValue("Entrega nombramiento");

        for (int i = 0; i < 21; i++) {
            Cell cell = subHeaderRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (i == 0) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 1) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 2) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 3) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 4) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 5) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i < 14) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 14) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER1));
            } else if (i == 15) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER2));
            } else if (i < 20) {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER2));
            } else {
                cell.setCellStyle(cellStyleManager.getCellStyle(Estilos.HEADER2));
            }
        }

        for (Anfitrion anfitrion : acto.getAnfitriones()) {
            if (!anfitrion.getInvitadosAUnActo(acto).isEmpty()) {
                Row row = sheet.createRow(fila++);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(anfitrion.getNombre() + " " + anfitrion.getPrimerApellido() + " " + anfitrion.getSegundoApellido());
                row.createCell(2).setCellValue(anfitrion.getUnidadDeFormacion());

                CellStyle currentStyle = useColorStyle1 ? cellStyleManager.getCellStyle(Estilos.NORMAL1) : cellStyleManager.getCellStyle(Estilos.NORMAL2);
                row.getCell(0).setCellStyle(currentStyle);
                row.getCell(1).setCellStyle(currentStyle);
                row.getCell(2).setCellStyle(currentStyle);

                boolean primerInvitado = true;
                for (Invitado invitado : anfitrion.getInvitadosAUnActo(acto)) {
                    Row rowInvitado;
                    if (primerInvitado) {
                        rowInvitado = row;
                        primerInvitado = false;
                    } else {
                        rowInvitado = sheet.createRow(fila++);
                    }

                    TipoDeZona tipoInvitacion = invitado.getLocalidad().getZonaConfigurada().getZona().getTipoDeZona();

                    String tipo = switch (tipoInvitacion) {
                        case TRIBUNA -> "T";
                        case GENERICA -> "P";
                        case ACOTADO -> "A";
                        default -> "";
                    };

                    rowInvitado.createCell(3).setCellValue(tipo);
                    rowInvitado.createCell(4).setCellValue(invitado.getLocalidad().getZonaConfigurada().getZona().getNombre());

                    int numeroAsiento = invitado.getLocalidad().getLocalidad() instanceof LocalidadNumerada localidadNumerada ? localidadNumerada.getNumero() : 0;
                    rowInvitado.createCell(5).setCellValue(numeroAsiento);
                    rowInvitado.createCell(6).setCellValue(invitado.getDni());
                    rowInvitado.createCell(7).setCellValue(invitado.getNombre());
                    rowInvitado.createCell(8).setCellValue(invitado.getPrimerApellido());
                    rowInvitado.createCell(9).setCellValue(invitado.getSegundoApellido());
                    rowInvitado.createCell(10).setCellValue(invitado.getFechaNacimiento().toString());
                    rowInvitado.createCell(11).setCellValue(invitado.getParentesco());

                    if (invitado.getCoche() != null) {
                        rowInvitado.createCell(12).setCellValue(invitado.getCoche().getMatricula());
                        rowInvitado.createCell(13).setCellValue(invitado.getCoche().getMarca() + " - " + invitado.getCoche().getModelo());
                        rowInvitado.createCell(14).setCellValue(invitado.getCoche().getColor());
                    }

                    if (invitado instanceof InvitadoFcse invitadoFcse) {
                        rowInvitado.createCell(15).setCellValue(invitadoFcse.getCuerpo().name());
                        rowInvitado.createCell(16).setCellValue(invitadoFcse.getEmpleo().getNombre());
                        rowInvitado.createCell(17).setCellValue(String.valueOf(invitadoFcse.getSituacion()));
                        rowInvitado.createCell(18).setCellValue(invitadoFcse.getAsisteDeUniforme() ? "Si" : "No");
                        rowInvitado.createCell(19).setCellValue(invitadoFcse.getRequiereVestuario() ? "Si" : "No");
                        rowInvitado.createCell(20).setCellValue(invitadoFcse.getEntregaNombramiento() ? "Si" : "No");
                    }

                    for (int i = 0; i < 21; i++) {
                        Cell cell = rowInvitado.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        if (i >= 15) {
                            cell.setCellStyle(useColorStyle1 ? cellStyleManager.getCellStyle(Estilos.NORMAL3) : cellStyleManager.getCellStyle(Estilos.NORMAL4));
                        } else {
                            cell.setCellStyle(currentStyle);
                        }
                    }
                }
                useColorStyle1 = !useColorStyle1;
            }
        }
        for (int i = 0; i < 21; i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }

    private static Set<AnfitrionPostModel> parseCSV(MultipartFile file) throws IOException {
        Set<AnfitrionPostModel> anfitriones = new HashSet<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .withCSVParser(parser)
                .build()) {
            reader.readNext(); // Omito el encabezado. Borrar esta línea si el archivo CSV no tiene encabezado de nombre, apellidos, etc.
            String[] line;
            while ((line = reader.readNext()) != null) {
                AnfitrionPostModel anfitrion = new AnfitrionPostModel();
                anfitrion.setNombre(line[0]);
                anfitrion.setPrimerApellido(line[1]);
                anfitrion.setSegundoApellido(line[2]);
                anfitrion.setUnidadDeFormacion(line[3]);
                anfitrion.setEmail(line[4]);
                anfitrion.setTelefono(line[5]);
                anfitriones.add(anfitrion);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return anfitriones;
    }

    private static Set<AnfitrionPostModel> parseExcel(MultipartFile file) throws IOException {
        Set<AnfitrionPostModel> anfitriones = new HashSet<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                AnfitrionPostModel anfitrion = new AnfitrionPostModel();
                anfitrion.setNombre(row.getCell(0).getStringCellValue());
                anfitrion.setPrimerApellido(row.getCell(1).getStringCellValue());
                anfitrion.setSegundoApellido(row.getCell(2).getStringCellValue());
                anfitrion.setDni(row.getCell(3).getStringCellValue());
                anfitrion.setEmail(row.getCell(4).getStringCellValue());

                String telefono;
                Cell phoneCell = row.getCell(5);

                if (phoneCell.getCellType() == CellType.NUMERIC) {
                    telefono = String.valueOf((long) phoneCell.getNumericCellValue());
                } else {
                    telefono = phoneCell.getStringCellValue();
                }
                anfitrion.setTelefono(telefono);

                anfitrion.setUnidadDeFormacion(row.getCell(6).getStringCellValue());

                anfitriones.add(anfitrion);
            }
        }
        return anfitriones;
    }
}