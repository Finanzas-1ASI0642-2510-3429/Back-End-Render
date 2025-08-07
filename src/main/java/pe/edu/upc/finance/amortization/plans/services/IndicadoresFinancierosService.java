package pe.edu.upc.finance.amortization.plans.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.finance.amortization.plans.entities.Pago;

import java.time.LocalDate;
import java.util.List;

@Service
public class IndicadoresFinancierosService {

    public double calcularTCEA(List<Pago> pagos, double valorRecibido, int pagosPorAnio) {
        double tasaMin = 0.00001;
        double tasaMax = 2.0; // hasta 200%
        double tolerancia = 0.0001;
        double r = 0;

        for (int i = 0; i < 100; i++) {
            r = (tasaMin + tasaMax) / 2;
            double npv = 0;

            for (Pago pago : pagos) {
                npv += pago.getCuota() / Math.pow(1 + r, pago.getNumeroCuota());
            }

            if (Math.abs(npv - valorRecibido) < tolerancia) {
                break;
            }

            if (npv > valorRecibido) {
                tasaMin = r;
            } else {
                tasaMax = r;
            }
        }

        return Math.pow(1 + r, pagosPorAnio) - 1;
    }

    // ✅ Tasa de Rendimiento Efectivo Anual (TREA) – Inversor
    public double calcularTREA(List<Pago> pagos, double precioCompra) {
        double tol = 0.00001;
        int maxIter = 100;
        double tasaMin = 0.000001;
        double tasaMax = 1.0;
        int frecuencia = obtenerFrecuenciaAnual(pagos);
        double r = 0;

        for (int iter = 0; iter < maxIter; iter++) {
            r = (tasaMin + tasaMax) / 2.0;
            double npv = 0;

            for (Pago pago : pagos) {
                npv += pago.getCuota() / Math.pow(1 + r, pago.getNumeroCuota());
            }

            if (Math.abs(npv - precioCompra) < tol) {
                return Math.pow(1 + r, frecuencia) - 1; // Retorna anualizada
            }

            if (npv > precioCompra) {
                tasaMin = r;
            } else {
                tasaMax = r;
            }
        }

        return -1; // No converge
    }


    // ✅ Duración de Macaulay
    public double calcularDuracion(List<Pago> pagos, double tasaPeriodo) {
        double duracion = 0;
        double valorActualTotal = 0;

        for (Pago pago : pagos) {
            double vp = pago.getCuota() / Math.pow(1 + tasaPeriodo, pago.getNumeroCuota());
            valorActualTotal += vp;
            duracion += pago.getNumeroCuota() * vp;
        }

        return duracion / valorActualTotal;
    }

    // ✅ Duración Modificada
    public double calcularDuracionModificada(double duracion, double tasaPeriodo) {
        return duracion / (1 + tasaPeriodo);
    }

    // ✅ Convexidad
    public double calcularConvexidad(List<Pago> pagos, double tasaPeriodo) {
        double convexidad = 0;
        double valorActualTotal = 0;

        for (Pago pago : pagos) {
            int t = pago.getNumeroCuota();
            double vp = pago.getCuota() / Math.pow(1 + tasaPeriodo, t);
            valorActualTotal += vp;
            convexidad += t * (t + 1) * vp;
        }

        return convexidad / (Math.pow(1 + tasaPeriodo, 2) * valorActualTotal);
    }

    // ✅ Detectar frecuencia de pago (mensual = 12, bimestral = 6, etc.)
    private int obtenerFrecuenciaAnual(List<Pago> pagos) {
        if (pagos.size() < 2) return 1;

        LocalDate fecha1 = pagos.get(0).getFechaPago();
        LocalDate fecha2 = pagos.get(1).getFechaPago();

        int meses = fecha2.getMonthValue() - fecha1.getMonthValue();
        int anios = fecha2.getYear() - fecha1.getYear();
        meses += anios * 12;

        if (meses <= 0) meses = 1; // Fallback en caso de error

        return 12 / meses;
    }
}