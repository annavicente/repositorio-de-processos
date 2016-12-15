package entidade;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterfaceM {
    
    // Requisito desejavel
    public Modelo atualizar(){
        return abrir();
    }
    
    public void excluir(){
        
    }
    // ======================== //
    public void salvar(Modelo mod){
        
        System.out.print("Informe o Diretorio do arquivo: ");
        if (salvarArq(Input.Str(), mod))
            System.out.println("Arquivo salvo com sucesso!");
        else{
            System.out.println("Falha em salvar o arquivo");
            
        }
    }
    
    public boolean salvarArq(String dir, Modelo mod){
        EntryFile arq;
        try {
            arq = new EntryFile(dir);
            arq.append(mod);
            arq.close();
        } catch (IOException ex) {
            ex.printStackTrace() ;
            return false;
        }
        return true;
    }
    
    public Modelo abrir(){
        Modelo m;
        
        System.out.print("Informe o local do arquivo modelo: ");
        try{
            EntryFile arq = new EntryFile(Input.Str());
            m = (Modelo) arq.read(0);
            arq.close();
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Erro ao abrir o arquivo.");
            e.printStackTrace();
            return null;
        }
        return m;
    }
    
    public void visualizar(Modelo mod){
        mod.print();
    }
    
    public Modelo novo(){
        char cod;
        String desc;
        Regra temp;
        int check;
        
        Modelo mod = novoModelo();
        
        System.out.print("Dominio:\nDeseja inserir um objeto[1/0] ");
        check = Input.Int();
        
        // Criação e Inserção do Dominio
        while (check != 0){
            System.out.print("Evento - 0\nAtividade - 1\nSubProcesso - 2\nInforme o objeto: ");
            check = Input.Int();
            if (check%3 == 0){
                mod.setDominio((ObjetodeFluxo) novoEvento());
            }else{
                if(check%3 == 1){
                    mod.setDominio((ObjetodeFluxo) novaAtividade());
                }else{
                    mod.setDominio((ObjetodeFluxo) novoSP());
                }
            }
            System.out.print("Dominio:\nDeseja inserir outro objeto[1/0] ");
            check = Input.Int();
        }
        
        System.out.print("Regras:\nDeseja inserir uma Regra[1/0] ");
        check = Input.Int();
        // Criação e Inserção de Regras
        while (check != 0){
            
            System.out.print("Informe o codigo: ");
            cod = Input.Char();
            System.out.print("Informe a descrição: ");
            desc = Input.Str();
            temp = new Regra(new TipoRegra(cod,desc));
            
            System.out.print("Deseja inserir Lado Esquerdo?[1/0] ");
            check = Input.Int();
            // Inserção dos Dominios esquerdos da regra
            while (check != 0){
                System.out.print("Evento - 0\nAtividade - 1\nSubProcesso - 2\nInforme o objeto: ");
                check = Input.Int();
                if (check%3 == 0){
                    temp.setEsquerdo((ObjetodeFluxo) novoEvento());
                }else{
                    if(check%3 == 1){
                        temp.setEsquerdo((ObjetodeFluxo) novaAtividade());
                    }else{
                        temp.setEsquerdo((ObjetodeFluxo) novoSP());
                    }
                }
                System.out.print("\nDeseja inserir outro objeto[1/0] ");
                check = Input.Int();
            }
            
            System.out.print("Deseja inserir Lado Direito?[1/0] ");
            check = Input.Int();
            // Inserção dos Dominios direito da regra
            while (check != 0){
                System.out.print("Evento - 0\nAtividade - 1\nSubProcesso - 2\nInforme o objeto: ");
                check = Input.Int();
                if (check%3 == 0){
                    temp.setDireito((ObjetodeFluxo) novoEvento());
                }else{
                    if(check%3 == 1){
                        temp.setDireito((ObjetodeFluxo) novaAtividade());
                    }else{
                        temp.setDireito((ObjetodeFluxo) novoSP());
                    }
                }
                System.out.print("\nDeseja inserir outro objeto[1/0] ");
                check = Input.Int();
            }
            
            mod.setRegra(temp);
            
            System.out.print("Deseja inserir outra Regra[1/0] ");
            check = Input.Int();
        
        }
        
        return mod;
    }
    
    public TipoRecurso novoTR(){
        
        TipoRecurso tr;
        System.out.print("Deseja um Recurso Humano ou Equipamento [1/0]: ");
        int check = Input.Int();
        if (check%2 == 1){
            tr = (TipoRecurso) new Humano();
            System.out.print("Informe o ID: ");
            tr.setId(Input.Int());
            System.out.print("Informe a qualificação: ");
            ((Humano)tr).setQualificacao(Input.Int());
            System.out.print("Informe a papel: ");
            ((Humano)tr).setPapel(Input.Str());
        }else{
            tr = (TipoRecurso) new Equipamento();
            System.out.print("Informe o ID: ");
            tr.setId(Input.Int());
            System.out.print("Informe o Codigo Tipo: ");
            ((Equipamento)tr).setCodigoTipo(Input.Int());
            System.out.print("Informe a Descrição: ");
            ((Equipamento)tr).setDescricao(Input.Str());
        }
        return tr;
    }
    
    public Evento novoEvento(){
        
        Evento atv = new Evento();
        System.out.print("Informe o ID: ");
        atv.setId(Input.Int());
        System.out.print("Informe o nome: ");
        atv.setNome(Input.Str());
        System.out.print("Informe a descrição: ");
        atv.setDescricao(Input.Str());
        System.out.print("Informe o lançamento: ");
        atv.setLancamento(Input.Boolean());
        
        
        return atv;
    
    }
    
    public Modelo novoModelo(){
        
        Modelo mod = new Modelo();
        System.out.print("Informe o nome do modelo: ");
        mod.setNome(Input.Str());
        System.out.print("Informe o ID do modelo: ");
        mod.setId(Input.Int());
        System.out.print("Informe a descrição do modelo: ");
        mod.setDesc(Input.Str());
        return mod;
    }
    
    public SubProcesso novoSP(){
        
        SubProcesso atv = new SubProcesso();
        System.out.print("Informe o ID: ");
        atv.setId(Input.Int());
        System.out.print("Informe o nome: ");
        atv.setNome(Input.Str());
        System.out.print("Informe a descrição: ");
        atv.setDescricao(Input.Str());
        System.out.print("Informe a Duração: ");
        atv.setDE(Input.Int());
        atv.setTR(novoTR());
        return atv;
    }
    
    public Atividade novaAtividade(){
        
        Atividade atv = new Atividade();
        System.out.print("Informe o ID: ");
        atv.setId(Input.Int());
        System.out.print("Informe o nome: ");
        atv.setNome(Input.Str());
        System.out.print("Informe a descrição: ");
        atv.setDescricao(Input.Str());
        System.out.print("Informe a Duração: ");
        atv.setDE(Input.Int());
        atv.setTR(novoTR());
        return atv;
    }
    
	public static void main(String[] args) throws IOException{
            
            InterfaceM inter = new InterfaceM();
            Modelo mod = null;
            int check = 1, menu = 5;
            while (check != 0){
                System.out.println("[1] Novo\n[2] Abrir\n[3] Salvar \n[4] Visualizar\n[5] Instanciar\n[0] Sair\nSelecione a ação desejada: ");
                check = Input.Int();
                if(check%menu == 1){
                    mod = inter.novo();
                }else{
                    if (check %menu == 2){
                        mod = inter.abrir();
                    }else{
                        if (check%menu ==3){
                            if(mod != null)
                                inter.salvar(mod);
                        }else{
                            if (check%menu == 4)
                                inter.visualizar(mod);
                            else {
                                 if(check%menu == 5)
                                     System.out.println("Partiu instancia..");
                            }
                        }
                    }
                }
            }
        }
}

/* TODO: Colocar alternativa 5 para ir para a instancia */