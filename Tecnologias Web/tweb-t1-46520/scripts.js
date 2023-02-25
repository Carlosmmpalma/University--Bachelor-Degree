/*REGISTO DO ANUNCIO*/
function registo_anuncio(event) {

    event.preventDefault();

    //RECEBE OS VALORES DO FORM E SERIALIZA
    var formValues = $("#myform").serialize();
    var tipo = document.getElementById("myform").elements["tipo"];
    tipo = tipo.value;

    // ENVIA OS DADOS UTILIZANDO O METODO POST
    if (tipo === "oferta") {
        var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/registaoferta", formValues);
    } else if (tipo === "procura") {
        var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/registaprocura", formValues);
    };

    // AVISA QUE OS DADOS FORAM ENVIADOS
    posting.done(function(data) {
        alert("ANUNCIO REGISTADO " + "AID:" + data.aid);
    });
}

//PROCURA DE ANUNCIOS
function listar_anuncios(event) {

    event.preventDefault();

    //RECEBE OS VALORES DO FORM 
    var formValues = $("#procura").serialize();


    //ENVIA VALORES DO FORM
    var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/roomsearch", formValues);

    //Função onde são tratados valores recebidos pelo servidor
    posting.done(function(data) {
        //LIMPAR A DIV PARA PUBLICAR NOVOS RESULTADOS
        var div = document.getElementById("anuncios");

        while (div.firstChild) {
            div.removeChild(div.firstChild);
        }

        //DIV PARA LISTAGEM FUTURA DE ANUNCIOS
        var div1 = document.createElement("div");
        div.appendChild(div1);

        //CALCULO DO N DE PAGS NECESSÁRIAS
        var obj = paginacao(data.resultados, 0, 4);
        var n_pags = obj.pags;

        //CRIAR N DE BOTOES CONSOANTE PAGS NECESSÁRIAS
        for (var i = 0; i < n_pags; i++) {
            var b = document.createElement("button");
            b.innerHTML = i + 1;
            b.classList.add("b");
            div.appendChild(b);
        }

        //EM CASO DE CLICK NOS BOTOES DE PAGINAÇAO
        $(".b").click(function(event) {

            event.preventDefault();
            var n = jQuery(this).text();
            var obj1 = paginacao(data.resultados, n, 4);


            while (div1.firstChild) {
                div1.removeChild(div1.firstChild);
            }

            //LISTAR OS AIDS
            for (var i = 0; i < obj1.obj.length; i++) {
                let aid = obj1.obj[i].aid;
                var p = document.createElement("p");
                p.innerHTML = aid;
                p.classList.add("anun_aid");
                div1.appendChild(p);
            }

            //AO CLICAREM NUM DOS AIDS DA LISTAGEM O ANUNCIO É DETALHADO
            $(".anun_aid").click(function(event) {
                event.preventDefault();
                var aid2 = jQuery(this).text();
                var div2 = document.getElementById("anuncio");

                //LIMPAR AS DUAS DIVS, RESPONSÁVEIS PELA LISTAGEM E POR MOSTRAR OS DETALHES DO ANUNCIO

                while (div2.firstChild) {
                    div2.removeChild(div2.firstChild);
                }

                detalhar_anuncio(aid2, "anuncio");
            });


        })


    });
}

//ANUNCIOS DE OFERTA EM DESTAQUE NA HOMEPAGE
function oferta_destaque() {

    var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/roomsearch", "tipo=oferta");
    var count = 0;

    posting.done(function(data) {

        for (var i = data.resultados.length - 1; i >= 0; i--) {

            //ULTIMOS 3 ANUNCIOS ATIVOS
            if (data.resultados[i].estado === "ativo" && count < 3) {
                let aid = data.resultados[i].aid;
                detalhar_anuncio(aid, "anun_oferta");

                count++;
            }
        }

    });

}

//ANUNCIOS DE PROCURA EM DESTAQUE NA HOMEPAGE
function procura_destaque() {

    var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/roomsearch", "tipo=procura");
    var count = 0;


    posting.done(function(data) {

        for (var i = data.resultados.length - 1; i >= 0; i--) {

            //ULTIMOS 3 ANUNCIOS ATIVOS
            if (data.resultados[i].estado === "ativo" && count < 3) {

                let aid = data.resultados[i].aid;
                detalhar_anuncio(aid, "anun_procura");

                count++;
            }
        }

    });
}

//FUNÇÃO PARA APRESENTAR OS DETALHES DE UM ANUNCIO
function detalhar_anuncio(aid, id_anun) {

    //OBTER DADOS RELATIVOS AO ANUNCIO COM O AID EM CAUSA
    var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/anuncio", "aid=" + aid);

    posting.done(function(data) {

        //DETALHAR O ANUNCIO
        var nome = id_anun;
        var id_anun1 = document.createElement("div");
        id_anun = document.getElementById(id_anun);
        id_anun.appendChild(id_anun1);


        let h = document.createElement("h3");
        let aid2 = document.createElement("p");
        let tipo_alojamento = document.createElement("p");
        let preco = document.createElement("p");
        let genero = document.createElement("p");
        let zona = document.createElement("p");
        let detalhes = document.createElement("p");
        let anunciante = document.createElement("p");
        let contacto = document.createElement("p");

        h.innerHTML = "ANUNCIO:";

        aid2.innerHTML = "Aid:" + aid;
        anunciante.innerHTML = "Anunciante: " + data.anuncio.anunciante;
        tipo_alojamento.innerHTML = "Tipo alojamento: " + data.anuncio.tipo_alojamento;
        preco.innerHTML = "Preço: " + data.anuncio.preco;
        genero.innerHTML = "Genero: " + data.anuncio.genero;
        zona.innerHTML = "Zona: " + data.anuncio.zona;
        detalhes.innerHTML = "Detalhes: " + data.anuncio.detalhes;
        contacto.innerHTML = "Contacto: " + data.anuncio.contacto;

        id_anun1.appendChild(h);
        id_anun1.appendChild(aid2);
        id_anun1.appendChild(anunciante);
        id_anun1.appendChild(tipo_alojamento);
        id_anun1.appendChild(preco);
        id_anun1.appendChild(genero);
        id_anun1.appendChild(zona);
        id_anun1.appendChild(detalhes);
        id_anun1.appendChild(contacto);

        mensagem(aid, id_anun1);

        //SÓ É POSSIVEL ACEDER AS MENSAGENS ATRAVÉS DA ABA ANUNCIOS, N ATRAVÉS DOS DESTAQUES
        if (nome !== "anun_oferta" && nome !== "anun_procura") {

            var butao = document.createElement("button");
            butao.innerHTML = "UTILIZADOR RESGISTADO";
            butao.classList.add("butao_registo");

            id_anun1.appendChild(butao);
        }

        //AO CLICKAR OBTEM AS MENSSAGENS RELATIVAS AO AID
        $(".butao_registo").click(function() {
            utilizador_resgistado(aid);
        })

    });
}

//LISTAR OS ANUNCIOS INAIVOS E ATIVOS NA GESTÃO DA PLANTAFORMA
function gere_anuncios() {

    var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/gereanuncios");

    posting.done(function(data) {

        //ANUNCIOS ATIVOS
        let ativos = document.getElementById("anuncios_ativos");
        let h = document.createElement("h3");
        h.innerHTML = "ANUNCIOS ATIVOS: ";
        ativos.appendChild(h);

        //DIV PARA LISTAGEM FUTURA DE ANUNCIOS ATIVOS
        var div1 = document.createElement("div");
        ativos.appendChild(div1);

        //CALCULO DO N DE PAGS NECESSÁRIAS
        var obj = paginacao(data.ativo, 0, 4);
        var n_pags = obj.pags;

        //CRIAR N DE BOTOES CONSOANTE PAGS NECESSÁRIAS
        for (var i = 0; i < n_pags; i++) {
            var b = document.createElement("button");
            b.innerHTML = i + 1;
            b.classList.add("b_at");
            ativos.appendChild(b);
        }

        $(".b_at").click(function(event) {
            event.preventDefault();
            var n = jQuery(this).text();
            var obj1 = paginacao(data.ativo, n, 4);


            while (div1.firstChild) {
                div1.removeChild(div1.firstChild);
            }

            for (var i = 0; i < obj1.obj.length; i++) {
                let aid = document.createElement("p");
                aid.innerHTML = obj1.obj[i];
                aid.classList.add("ativo");
                div1.appendChild(aid);
            }

            //CLICK NUM ANUNCIO ATIVO
            $(".ativo").click(function(event) {
                event.preventDefault();
                var aid2 = jQuery(this).text();

                var div = document.getElementById("conteudo");

                while (div.firstChild) {
                    div.removeChild(div.firstChild);
                }

                detalhar_anuncio(aid2, "conteudo");

                criar_form(aid2, "inativo", "DESATIVAR"); //CRIA O FORM E TRANSFORMA UM ANUNCIO ATIVO EM INATIVO

            });


        })

        //ANUNCIOS INATIVOS
        let inativos = document.getElementById("anuncios_inativos");
        let h2 = document.createElement("h3");
        h2.innerHTML = "ANUNCIOS INATIVOS: ";
        inativos.appendChild(h2);

        var obj = paginacao(data.inativo, 0, 4);
        var n_pags = obj.pags;

        //DIV PARA LISTAGEM FUTURO DE ANUNCIOS INATIVOS
        var div2 = document.createElement("div");
        inativos.appendChild(div2);

        for (var i = 0; i < n_pags; i++) {
            var b = document.createElement("button");
            b.innerHTML = i + 1;
            b.classList.add("b_inat");
            inativos.appendChild(b);


        }

        $(".b_inat").click(function(event) {
            event.preventDefault();
            var n = jQuery(this).text();
            var obj1 = paginacao(data.inativo, n, 4);


            while (div2.firstChild) {
                div2.removeChild(div2.firstChild);
            }

            for (var i = 0; i < obj1.obj.length; i++) {
                let aid = document.createElement("p");
                aid.innerHTML = obj1.obj[i];
                aid.classList.add("inativo");
                div2.appendChild(aid);
            }

            //CLICK NUM ANUNCIO INATIVO
            $(".inativo").click(function(event) {
                event.preventDefault();
                var aid2 = jQuery(this).text();

                var div = document.getElementById("conteudo");

                while (div.firstChild) {
                    div.removeChild(div.firstChild);
                }

                detalhar_anuncio(aid2, "conteudo");
                criar_form(aid2, "ativo", "ATIVAR"); //CRIA O FORM E TRANSFORMA UM ANUNCIO INATIVO EM ATIVO

            });


        })

    })
}

//ATIVAÇÃO E DESATIVAÇÃO DOS ANUNCIOS 
function criar_form(aid, value1, value2) {

    div = document.getElementById("form_gestor");


    var tit = document.createElement("h3");
    var hdescricao = document.createElement("p");
    var l_break = document.createElement("br");

    tit.innerHTML = "CONTROL DE ANUNCIO: ";
    hdescricao.innerHTML = "DESCRIÇÃO: ";

    // CRIAR UM FORM
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("id", "form_gestor2");
    form.setAttribute("action", "http://alunos.di.uevora.pt/tweb/t1/controloanuncio");

    // ENVIO DO AID ATRAVÉS DO FORM
    var aid3 = document.createElement("input");
    aid3.setAttribute("type", "hidden");
    aid3.setAttribute("name", "aid");
    aid3.setAttribute("value", aid);

    // INPUT PARA A DESCRIÇAO
    var descricao = document.createElement("textarea");
    descricao.name = 'descricao';
    descricao.maxLength = 5000;
    descricao.cols = 60;
    descricao.rows = 10;

    // INPUT PARA A ESTADO
    var estado = document.createElement("input");
    estado.setAttribute("type", "hidden");
    estado.setAttribute("name", "estado");
    estado.setAttribute("value", value1);

    // BOTÃO DE SUBMISSÃO
    var s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("id", "button");
    s.setAttribute("value", value2);

    form.appendChild(aid3);
    form.appendChild(tit);
    form.appendChild(hdescricao);
    form.appendChild(descricao);
    form.appendChild(estado);
    form.appendChild(l_break);
    form.appendChild(s);

    div.appendChild(form);

    //SUBMETER O FORM COM AJAX

    $("#form_gestor2").submit(function(e) {

        e.preventDefault();

        var form = $(this);
        var actionUrl = form.attr('action');

        $.ajax({
            type: "POST",
            url: actionUrl,
            data: form.serialize(),
            success: function() {
                alert("Anuncio: " + value1);
            }
        });

    });

}

//FORM PARA ENVIO DE MENSAGENS
function mensagem(aid, id_anun) {

    var div = document.createElement("div_mensagem");
    id_anun.appendChild(div);

    var tit = document.createElement("h3");
    var hremetente = document.createElement("a");
    var hmensagem = document.createElement("p");
    var l_break = document.createElement("br");

    tit.innerHTML = "ENVIAR MENSAGEM: ";
    hremetente.innerHTML = "REMETENTE: ";
    hmensagem.innerHTML = "MENSAGEM: ";

    // CRIAR UM FORM
    var form = document.createElement("form");
    form.setAttribute("id", "form_mensagem")
    form.setAttribute("method", "post");
    form.setAttribute("action", "http://alunos.di.uevora.pt/tweb/t1/contactar");

    // ENVIO DO AID ATRAVÉS DO FORM
    var aid3 = document.createElement("input");
    aid3.setAttribute("type", "hidden");
    aid3.setAttribute("name", "aid");
    aid3.setAttribute("value", aid);

    // INPUT PARA A REMETENTE
    var remetente = document.createElement("input");
    remetente.setAttribute("type", "text");
    remetente.setAttribute("name", "remetente");

    // INPUT PARA A MENSSAGEM
    var mensagem = document.createElement("textarea");
    mensagem.name = 'msg';
    mensagem.maxLength = 5000;
    mensagem.cols = 60;
    mensagem.rows = 10;


    // BOTÃO DE SUBMISSÃO
    var s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("value", "ENVIAR");
    s.setAttribute("id", "button");

    form.appendChild(tit);
    form.appendChild(aid3);
    form.appendChild(hremetente);
    form.appendChild(remetente);
    form.appendChild(hmensagem);
    form.appendChild(mensagem);
    form.appendChild(l_break);

    form.appendChild(s);

    div.appendChild(form);

    //SUBMETER O FORM COM AJAX
    $("#form_mensagem").submit(function(e) {

        e.preventDefault();

        var form = $(this);
        var actionUrl = form.attr('action');

        $.ajax({
            type: "POST",
            url: actionUrl,
            data: form.serialize(),
            success: function() {
                alert("Mensagem enviada");
            }
        });

    });


}

//VISTA DE UTILIZADOR REGISTADO
function utilizador_resgistado(aid) {

    var div = document.getElementById("form_mensagem");

    //REMOVE ELEMENTOS PRESENTES NA DIV
    while (div.firstChild) {
        div.removeChild(div.firstChild);
    }

    var div1 = document.createElement("utilizador_r");
    div.appendChild(div1);
    var h3 = document.createElement("h3");
    h3.innerHTML = "MENSAGENS: "

    div1.appendChild(h3);

    var posting = $.post("http://alunos.di.uevora.pt/tweb/t1/mensagens", "aid=" + aid);

    //PERCORRE O OBJETO RETORNADO COM AS MENSAGENS E COLOCA NA DIV
    posting.done(function(data) {

        var n = 1;
        for (var i = 0; i < data.msgs.length; i++) {
            var remetente = data.msgs[i].remetente;
            var msg = data.msgs[i].msg;

            var h4 = document.createElement("h4");
            h4.innerHTML = "MENSAGEM " + n + ":";
            div1.appendChild(h4);

            var a = document.createElement("a");
            var p = document.createElement("p");
            a.innerHTML = "Remetente:" + remetente;
            p.innerHTML = "Mensagem:" + msg;

            div1.appendChild(a);
            div1.appendChild(p);
            n++;
        }
    })
}

//PAGINAÇÃO
//ALGORITMO PARA PAGINAÇÃO
function paginacao(objeto, pagina, colunas) {
    var inicio = (pagina - 1) * colunas;
    var fim = inicio + colunas;

    var objeto_t = objeto.slice(inicio, fim);

    var paginas = Math.ceil(objeto.length / colunas)

    //Retorna o obj com os anuncios correspondentes a pagina e o numero de paginas
    return {
        'obj': objeto_t,
        'pags': paginas,
    }
}