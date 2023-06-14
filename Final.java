package TrabalhoFinalV1;

public class Final extends Cliente {
    static final long serialVersionUID = 0;

public void setPreco(Float maoObra, Float precoPeca){
        super.preco = ((maoObra - (maoObra*0.03f))+precoPeca)*1.23f;
}
    public Final() {
        super();
        tipoCliente = 1;
    }
}
