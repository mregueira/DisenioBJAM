package servicios;

import entidades.LoteMedida;
import enumeraciones.EnumAnalisis;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leandro
 */
public class ServicioCota {

    private Double nMin = 0d;
    private Double nMax = 0d;
    private int decimales = 0;
    private List<Double> listaMedidas;
    private ServicioEstadistica est;
    
    
    public ServicioCota(){
    }

    public ServicioCota(String tol){
        calcularLimites(tol);
    }
    
    public void setTolerancia(String tol){
        calcularLimites(tol);
    }

    private void calcularLimites(String tol){
        if(tol == null || tol.isEmpty()){
            return;
        }

        //Tolerancia 15 ± 0.2
        if(tol.contains("±")){
            String nominal = "0";
            String tolerancia = "0";
            try{
                nominal = tol.substring(0, tol.indexOf("±") - 1).trim();
                tolerancia = tol.substring(tol.indexOf("±") + 1).trim();
                nMin = Double.parseDouble(nominal) - Double.parseDouble(tolerancia) ;
                nMax = Double.parseDouble(nominal) + Double.parseDouble(tolerancia) ;
            }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                nMin = 0d;
                nMax = 0d;
            }
            decimales(nominal, tolerancia);
        }

        //Tolerancia 14.80 / 15.20
        else if(tol.contains("/") &&
               !(tol.contains("-") || tol.contains("+"))){

            String minimo = tol.substring(0, tol.indexOf("/")).trim();
            String maximo = tol.substring(tol.indexOf("/") + 1).trim();

            try{
                nMin = Double.parseDouble(minimo);
                nMax = Double.parseDouble(maximo);
            }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                nMin = 0d;
                nMax = 0d;
            }
            decimales(minimo, maximo);
        }

        //Tolerancia 15.10  -0.30/+0.10
        else if(tol.contains("/") &&
               (tol.contains("-") || tol.contains("+"))){

            int indMenos = tol.indexOf("-");
            int indMas = tol.indexOf("+");
            int indBarra = tol.indexOf("/");

            String nominal = "";
            String tolMin = "";
            String tolMax = "";

            //15.10 -0.30/+0.10
            if(indMenos >= 0 && indMenos < indBarra && indBarra < indMas){
                nominal = tol.substring(0, indMenos).trim();
                tolMin = tol.substring(indMenos + 1, indBarra).trim();
                tolMax = tol.substring(indMas + 1).trim();
                try{
                    nMin = Double.parseDouble(nominal) - Double.parseDouble(tolMin) ;
                    nMax = Double.parseDouble(nominal) + Double.parseDouble(tolMax) ;
                }
                catch(NumberFormatException| StringIndexOutOfBoundsException e){
                    nMin = 0d;
                    nMax = 0d;
                }
            }
            //15.10 -0.30/-0.10
            else if(indMenos >= 0  && indMenos < indBarra && indMas < 0){
                nominal = tol.substring(0, indMenos).trim();
                tolMin = tol.substring(indMenos + 1, indBarra).trim();
                tolMax = tol.substring(tol.lastIndexOf("-") + 1).trim();
                try{
                    if(Double.parseDouble(tolMin) > Double.parseDouble(tolMax)){
                        nMin = Double.parseDouble(nominal) - Double.parseDouble(tolMin) ;
                        nMax = Double.parseDouble(nominal) - Double.parseDouble(tolMax) ;
                    }
                    else{
                        nMax = Double.parseDouble(nominal) - Double.parseDouble(tolMin) ;
                        nMin = Double.parseDouble(nominal) - Double.parseDouble(tolMax) ;
                    }
                }
                catch(NumberFormatException| StringIndexOutOfBoundsException e){
                    nMin = 0d;
                    nMax = 0d;
                }
            }
            //15.10 +0.30/+0.10
            else if(indMenos <  0 && indMas >= 0 && indMas  < indBarra){
                nominal = tol.substring(0, indMas).trim();
                tolMin = tol.substring(indMas + 1, indBarra).trim();
                tolMax = tol.substring(tol.lastIndexOf("+") + 1).trim();
                try{
                    if(Double.parseDouble(tolMin) < Double.parseDouble(tolMax)){
                        nMin = Double.parseDouble(nominal) + Double.parseDouble(tolMin);
                        nMax = Double.parseDouble(nominal) + Double.parseDouble(tolMax);
                    }
                    else{
                        nMax = Double.parseDouble(nominal) + Double.parseDouble(tolMin);
                        nMin = Double.parseDouble(nominal) + Double.parseDouble(tolMax);
                    }
                }
                catch(NumberFormatException| StringIndexOutOfBoundsException e){
                    nMin = 0d;
                    nMax = 0d;
                }
            }
            //15.10 +0.30/-0.10
            else if(indMas > 0 && indMas < indBarra && indBarra < indMenos){
                nominal = tol.substring(0, indMas).trim();
                tolMax = tol.substring(indMas + 1, indBarra).trim();
                tolMin = tol.substring(indMenos + 1).trim();
                try{
                    nMin = Double.parseDouble(nominal) - Double.parseDouble(tolMin);
                    nMax = Double.parseDouble(nominal) + Double.parseDouble(tolMax);
                }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                    nMin = 0d;
                    nMax = 0d;
                }
            }
            decimales(nominal, tolMin, tolMax);

        }
        //Tolerancia 15.20  -0.40
        else if(tol.contains("-")){
            String nominal = tol.substring(0, tol.indexOf("-") - 1).trim();
            String tolerancia = tol.substring(tol.indexOf("-") + 1).trim();
            try{
                nMin = Double.parseDouble(nominal) - Double.parseDouble(tolerancia);
                nMax = Double.parseDouble(nominal);
            }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                nMin = 0d;
                nMax = 0d;
            }
            decimales(nominal, tolerancia);
        }
        //Tolerancia 14.80  +0.40
        else if(tol.contains("+")){
            String nominal = tol.substring(0, tol.indexOf("+") - 1).trim();
            String tolerancia = tol.substring(tol.indexOf("+") + 1).trim();
            try{
                nMin = Double.parseDouble(nominal);
                nMax = Double.parseDouble(nominal) + Double.parseDouble(tolerancia);
            }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                nMin = 0d;
                nMax = 0d;
            }
            decimales(nominal, tolerancia);
        }
        //Tolerancia 15.20 Máximo
        else if(tol.contains("Máx")){
            try{
                nMin = 0d;
                try{
                    nMax = Double.parseDouble(tol.substring(0, tol.indexOf("M") - 1).trim());
                }
                catch(StringIndexOutOfBoundsException e){
                    nMax = 0d;
                }
                            
            }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                nMin = 0d;
                nMax = 0d;
            }
            decimales(tol.substring(0, tol.indexOf("M")).trim());

        }
        //Tolerancia 14.80 Mínimo. No funciona con Mínimo 5
        else if(tol.contains("Mín")){
            try{
                nMin = Double.parseDouble(tol.substring(0, tol.indexOf("M")).trim());
                nMax = nMin * 10;
            }
            catch(NumberFormatException| StringIndexOutOfBoundsException e){
                nMin = 0d;
                nMax = 0d;
            }
            decimales(tol.substring(0, tol.indexOf("M")).trim());
        }
    }

    public void setLista(List<Double> lista){
        this.listaMedidas = lista;
        est = new ServicioEstadistica(listaMedidas);
    }
    
    public void setListaMedidas(List<LoteMedida> lista){
        listaMedidas = new ArrayList<>();
        for(LoteMedida medida: lista){
            listaMedidas.add(medida.getValor());
        }
        est = new ServicioEstadistica(listaMedidas);
    }
    
    
    public double getCPK(){
        try{
            return Math.min(getMaximo() - est.getMedia(), est.getMedia() - getMinimo()) /  est.getDesviacionStd() / 3;
        }
        catch(NullPointerException e){
            return 0;
        }
    }

    public double getCP(){
        
        try{
            return (getMaximo() - getMinimo()) /  est.getDesviacionStd() / 6;
        }
        catch(NullPointerException e){
            return 0;
        }
    }
    
    public double getMedia(){
        return est.getMedia();
    }
    public double getDesviacionStd(){
        return est.getDesviacionStd();
    }
    
    
    public String getNombreMinimo(){
        return nMin.toString().trim();
    }
    public String getNombreMaximo(){
        return nMax.toString().trim();
    }
    public Double getMaximo() {
        return nMax;
    }
    public Double getMinimo() {
        return nMin;
    }
    public Integer getDecimales(int idTipoAnalisis){
        if(EnumAnalisis.CPK.getId().equals(idTipoAnalisis) ||
           EnumAnalisis.POR_VARIABLE.getId().equals(idTipoAnalisis)){
            return decimales;
        }                
        else{
            return -1;
        }
    }
    public Double getMedioDeTolerancia(){
        return nMin + ((nMax - nMin) / 2);
    }
    
    public int colorDeMedida(double valor){
        double amarilloMinimo = getMinimo() +  (getMaximo() - getMinimo()) * 0.10; 
        double amarilloMaximo = getMaximo() -  (getMaximo() - getMinimo()) * 0.11; 
        
        int rojo        = -65536;   //Rojo para Android
        int amarillo    = -256;     //Amarillo para Android
        int verde       = -16711936;//Verde para android
                
        if(valor < getMinimo()){
            return rojo;
        }
        if(valor > getMaximo()){
            return rojo;
        }
        if(valor <= amarilloMinimo){
            return amarillo;
        }
        if(valor >=  amarilloMaximo){
            return amarillo;
        }
        return verde;
    }
    
    /**
     * Calcula la cantidad de decimales según la mayor cantidad de decimales de los numeros1, 2 y 3.
     * Cualqueir parámetro puede ser null.
     * @param num1
     * @param num2
     * @param num3
     */
    private void decimales(String num1, String num2, String num3){
        decimales = 0;
        String decimal;
        if(num1 != null && num1.contains(".")){
            decimal = num1.substring(num1.indexOf(".") + 1).trim();
            decimales = decimal.length();
        }
        if(num2 != null && num2.contains(".")){
            decimal = num2.substring(num2.indexOf(".") + 1).trim();
            decimales = Math.max(decimales, decimal.length());
        }
        if(num3 != null && num3.contains(".")){
            decimal = num3.substring(num3.indexOf(".") + 1).trim();
            decimales = Math.max(decimales, decimal.length());
        }
    }

    private void decimales(String num1, String num2){
        decimales(num1, num2, null);
    }

    private void decimales(String num1){
        decimales(num1, null, null);
    }
    
    
    
    
    
}
