//ANUNCIOS EM DESTAQUE DO TIPO OFERTA
function destaque_oferta() {
    $(document).ready(function() {

        list_oferta = JSON.parse(list_oferta);
        console.log(list_oferta);

        if (list_oferta.length > 0) {
            for (var i = 0; i < list_oferta.length; i++) {

                var anuncio1 = document.createElement("div");
                var anuncio = document.getElementById("anun_oferta");
                anuncio.appendChild(anuncio1);

                let h = document.createElement("h3");
                let aid = document.createElement("p");
                let preco = document.createElement("p");
                let zona = document.createElement("p");
                aid.classList.add("aid")
                aid.setAttribute("id", i);

                h.innerHTML = "ANUNCIO:";
                aid.innerHTML = "Aid:" + list_oferta[i].AID;
                preco.innerHTML = "Preco: " + list_oferta[i].PRECO;
                zona.innerHTML = "Zona: " + list_oferta[i].ZONA;

                anuncio1.appendChild(h);
                anuncio1.appendChild(aid);
                anuncio1.appendChild(preco);
                anuncio1.appendChild(zona);
            }
        }

        $(".aid").click(function(event) {
            event.preventDefault();

            let destaque = document.getElementById("anun_destaque");
            destaque.innerHTML = "";

            let anuncio2 = document.createElement("div");
            anuncio2.classList.add("anun_destaque2");
            let index = event.target.id;
            anuncio2.innerHTML = ""


            let h = document.createElement("h3");
            let aid2 = document.createElement("p");
            let tipo_alojamento = document.createElement("p");
            let preco2 = document.createElement("p");
            let genero = document.createElement("p");
            let zona2 = document.createElement("p");
            let detalhes = document.createElement("p");
            let anunciante = document.createElement("p");
            let contacto = document.createElement("p");
            let tipo2 = document.createElement("p");

            h.innerHTML = "ANUNCIO:";

            aid2.innerHTML = "Aid:" + list_oferta[index].AID;
            anunciante.innerHTML = "Anunciante: " + list_oferta[index].USER_NAME;
            tipo_alojamento.innerHTML = "Tipo alojamento: " + list_oferta[index].TIPO_ALOJAMENTO;
            preco2.innerHTML = "Preco: " + list_oferta[index].PRECO;
            genero.innerHTML = "Genero: " + list_oferta[index].GENERO;
            zona2.innerHTML = "Zona: " + list_oferta[index].ZONA;
            detalhes.innerHTML = "Detalhes: " + list_oferta[index].DETALHES;
            contacto.innerHTML = "Contacto: " + list_oferta[index].CONTACTO;
            tipo2.innerHTML = "Tipo: " + list_oferta[index].TIPO;

            anuncio2.appendChild(h);
            anuncio2.appendChild(aid2);
            anuncio2.appendChild(tipo2);
            anuncio2.appendChild(tipo_alojamento);
            anuncio2.appendChild(preco2);
            anuncio2.appendChild(genero);
            anuncio2.appendChild(zona2);
            anuncio2.appendChild(anunciante);
            anuncio2.appendChild(detalhes);
            anuncio2.appendChild(contacto);

            destaque.appendChild(anuncio2);

        });
    });
}

//ANUNCIOS EM DESTAQUE DO TIPO PROCURA
function destaque_procura() {
    $(document).ready(function() {

        list_procura = JSON.parse(list_procura);
        console.log(list_procura);

        if (list_procura.length > 0) {
            for (var i = 0; i < list_procura.length; i++) {

                var anuncio1 = document.createElement("div");
                var anuncio = document.getElementById("anun_procura");
                anuncio.appendChild(anuncio1);

                let h = document.createElement("h3");
                let aid = document.createElement("p");
                let preco = document.createElement("p");
                let zona = document.createElement("p");
                aid.classList.add("aid2")
                aid.setAttribute("id", i);

                h.innerHTML = "ANUNCIO:";
                aid.innerHTML = "Aid:" + list_procura[i].AID;
                preco.innerHTML = "Preco: " + list_procura[i].PRECO;
                zona.innerHTML = "Zona: " + list_procura[i].ZONA;

                anuncio1.appendChild(h);
                anuncio1.appendChild(aid);
                anuncio1.appendChild(preco);
                anuncio1.appendChild(zona);
            }
        }

        $(".aid2").click(function(event) {
            event.preventDefault();

            let destaque = document.getElementById("anun_destaque");
            destaque.innerHTML = "";

            let anuncio3 = document.createElement("div");
            anuncio3.classList.add("anun_destaque2");
            let index = event.target.id;
            anuncio3.innerHTML = ""


            let h = document.createElement("h3");
            let aid2 = document.createElement("p");
            let tipo_alojamento = document.createElement("p");
            let preco2 = document.createElement("p");
            let genero = document.createElement("p");
            let zona2 = document.createElement("p");
            let detalhes = document.createElement("p");
            let anunciante = document.createElement("p");
            let contacto = document.createElement("p");
            let tipo2 = document.createElement("p");

            h.innerHTML = "ANUNCIO:";

            aid2.innerHTML = "Aid:" + list_procura[index].AID;
            anunciante.innerHTML = "Anunciante: " + list_procura[index].USER_NAME;
            tipo_alojamento.innerHTML = "Tipo alojamento: " + list_procura[index].TIPO_ALOJAMENTO;
            preco2.innerHTML = "Preco: " + list_procura[index].PRECO;
            genero.innerHTML = "Genero: " + list_procura[index].GENERO;
            zona2.innerHTML = "Zona: " + list_procura[index].ZONA;
            detalhes.innerHTML = "Detalhes: " + list_procura[index].DETALHES;
            contacto.innerHTML = "Contacto: " + list_procura[index].CONTACTO;
            tipo2.innerHTML = "Tipo: " + list_procura[index].TIPO;

            anuncio3.appendChild(h);
            anuncio3.appendChild(aid2);
            anuncio3.appendChild(tipo2);
            anuncio3.appendChild(tipo_alojamento);
            anuncio3.appendChild(preco2);
            anuncio3.appendChild(genero);
            anuncio3.appendChild(zona2);
            anuncio3.appendChild(anunciante);
            anuncio3.appendChild(detalhes);
            anuncio3.appendChild(contacto);

            destaque.appendChild(anuncio3);

        });

    });
}

//LISTAR OS ANUNCIOS
function listar() {
    $(document).ready(function() {
        lista_anuncios = JSON.parse(lista_anuncios);
        console.log(lista_anuncios);

        var anuncios = document.getElementById("anuncios");
        anuncios.innerHTML = "";

        if (lista_anuncios.length > 0) {
            //CALCULO DO N DE PAGS NECESSÁRIAS
            var obj = paginacao(lista_anuncios, 0, 4);
            var n_pags = obj.pags;

            //CRIAR N DE BOTOES CONSOANTE PAGS NECESSÁRIAS
            for (var i = 0; i < n_pags; i++) {
                var b = document.createElement("button");
                b.innerHTML = i + 1;
                b.classList.add("b_at");
                butoes1.appendChild(b);
            }


            $(".b_at").click(function(event) {
                event.preventDefault();
                var n = jQuery(this).text();
                var obj1 = paginacao(lista_anuncios, n, 4);

                while (anuncios.firstChild) {
                    anuncios.removeChild(anuncios.firstChild);
                }

                for (var i = 0; i < obj1.obj.length; i++) {
                    var anuncio = document.createElement("div");
                    anuncio.classList.add("anuncio");

                    let h = document.createElement("h3");
                    let aid = document.createElement("p");
                    let preco = document.createElement("p");
                    let zona = document.createElement("p");
                    aid.classList.add("aid");
                    aid.setAttribute("id", i);

                    h.innerHTML = "ANUNCIO:";
                    aid.innerHTML = "Aid:" + obj1.obj[i].AID;
                    preco.innerHTML = "Preco: " + obj1.obj[i].PRECO;
                    zona.innerHTML = "Zona: " + obj1.obj[i].ZONA;

                    anuncio.appendChild(h);
                    anuncio.appendChild(aid);
                    anuncio.appendChild(preco);
                    anuncio.appendChild(zona);

                    anuncios.appendChild(anuncio);
                }

                $(".aid").click(function(event) {
                    event.preventDefault();

                    let destaque = document.getElementById("conteudo_anun");
                    let but = document.getElementById("butoes1");
                    but.innerHTML = "";
                    destaque.innerHTML = "";

                    var anuncio3 = document.createElement("div");
                    anuncio3.setAttribute("id", "anuncio1");
                    var index = event.target.id;
                    anuncio3.innerHTML = ""
                    console.log(index);

                    let h = document.createElement("h3");
                    let aid2 = document.createElement("p");
                    let tipo_alojamento = document.createElement("p");
                    let preco2 = document.createElement("p");
                    let genero = document.createElement("p");
                    let zona2 = document.createElement("p");
                    let detalhes = document.createElement("p");
                    let anunciante = document.createElement("p");
                    let contacto = document.createElement("p");
                    let tipo2 = document.createElement("p");

                    h.innerHTML = "ANUNCIO:";

                    aid2.innerHTML = "Aid:" + obj1.obj[index].AID;
                    anunciante.innerHTML = "Anunciante: " + obj1.obj[index].USER_NAME;
                    tipo_alojamento.innerHTML = "Tipo alojamento: " + obj1.obj[index].TIPO_ALOJAMENTO;
                    preco2.innerHTML = "Preco: " + obj1.obj[index].PRECO;
                    genero.innerHTML = "Genero: " + obj1.obj[index].GENERO;
                    zona2.innerHTML = "Zona: " + obj1.obj[index].ZONA;
                    detalhes.innerHTML = "Detalhes: " + obj1.obj[index].DETALHES;
                    contacto.innerHTML = "Contacto: " + obj1.obj[index].CONTACTO;
                    tipo2.innerHTML = "Tipo: " + obj1.obj[index].TIPO;

                    anuncio3.appendChild(h);
                    anuncio3.appendChild(aid2);
                    anuncio3.appendChild(tipo2);
                    anuncio3.appendChild(tipo_alojamento);
                    anuncio3.appendChild(preco2);
                    anuncio3.appendChild(genero);
                    anuncio3.appendChild(zona2);
                    anuncio3.appendChild(anunciante);
                    anuncio3.appendChild(detalhes);
                    anuncio3.appendChild(contacto);

                    destaque.appendChild(anuncio3);
                    if (user != "null") {
                        if (user == obj1.obj[index].USER_NAME) {
                            // CRIAR UM FORM
                            let form = document.createElement("form");
                            form.setAttribute("method", "post");
                            form.setAttribute("id", "form");
                            form.setAttribute("action", "/own_msgs");

                            // ENVIO DO AID ATRAVÉS DO FORM
                            let aid3 = document.createElement("input");
                            aid3.setAttribute("type", "hidden");
                            aid3.setAttribute("name", "aid");
                            aid3.setAttribute("value", obj1.obj[index].AID);

                            // ENVIO DO AID ATRAVÉS DO FORM
                            let user_name = document.createElement("input");
                            user_name.setAttribute("type", "hidden");
                            user_name.setAttribute("name", "user_name");
                            user_name.setAttribute("value", obj1.obj[index].USER_NAME);

                            let s = document.createElement("input");
                            s.setAttribute("type", "submit");
                            s.setAttribute("id", "button");
                            s.setAttribute("value", "MENSAGENS")

                            let csrf_token = document.createElement("input");
                            csrf_token.setAttribute("type", "hidden");
                            csrf_token.setAttribute("name", "_csrf");
                            csrf_token.setAttribute("value", token);

                            form.appendChild(aid3);
                            form.appendChild(user_name);
                            form.appendChild(s);
                            form.appendChild(csrf_token);

                            anuncio3.appendChild(form);

                        } else {

                            // CRIAR UM FORM
                            let form_msg = document.createElement("div");
                            form_msg.setAttribute("id", "form_msg");
                            let form = document.createElement("form");
                            form.setAttribute("method", "post");
                            form.setAttribute("id", "form");
                            form.setAttribute("action", "/rgs_msg");

                            // ENVIO DO AID ATRAVÉS DO FORM
                            let aid3 = document.createElement("input");
                            aid3.setAttribute("type", "hidden");
                            aid3.setAttribute("name", "aid");
                            aid3.setAttribute("value", obj1.obj[index].AID);

                            // ENVIO DO AID ATRAVÉS DO FORM
                            let user_name = document.createElement("input");
                            user_name.setAttribute("type", "hidden");
                            user_name.setAttribute("name", "user_name");
                            user_name.setAttribute("value", obj1.obj[index].USER_NAME);

                            // ENVIO DO NOME ATRAVÉS DO FORM
                            let nome_p = document.createElement("p");
                            nome_p.innerHTML = "NOME: "
                            let nome = document.createElement("input");
                            nome.setAttribute("type", "text");
                            nome.setAttribute("name", "nome");
                            nome_p.appendChild(nome);

                            // INPUT PARA A MENSAGEM
                            let msg_p = document.createElement("p");
                            msg_p.innerHTML = "MENSAGEM: "
                            let mensagem = document.createElement("textarea");
                            mensagem.name = 'mensagem';
                            mensagem.maxLength = 5000;
                            mensagem.cols = 60;
                            mensagem.rows = 10;
                            let n = document.createElement("p");

                            let s = document.createElement("input");
                            s.setAttribute("type", "submit");
                            s.setAttribute("id", "button");
                            s.setAttribute("value", "Enviar");


                            let csrf_token = document.createElement("input");
                            csrf_token.setAttribute("type", "hidden");
                            csrf_token.setAttribute("name", "_csrf");
                            csrf_token.setAttribute("value", token);

                            form.append(aid3);
                            form.append(user_name);
                            form.append(nome_p);
                            form.append(msg_p);
                            form.append(mensagem);
                            form.append(n);
                            form.append(s);
                            form.appendChild(csrf_token);


                            form_msg.appendChild(form);
                            destaque.appendChild(form_msg);

                        }
                    }

                });

            });


        }


    });
}

//LISTAR MENSAGENS
function msg_list() {

    list_msgs = JSON.parse(list_msgs);
    console.log(list_msgs);

    if (list_msgs.length > 0) {
        let mensagens = document.getElementById("mensagens2");

        for (let i = 0; i < list_msgs.length; i++) {
            let new_msg = document.createElement("div")
            new_msg.setAttribute("id", "div_msg");
            let h = document.createElement("h3");
            h.innerHTML = "Mensagem " + (i + 1) + ": ";
            let remetente = document.createElement("p");
            remetente.innerHTML = "USERNAME DO REMETENTE: " + list_msgs[i].USER_NAME;
            let nome = document.createElement("p");
            nome.innerHTML = "NOME DO REMETENTE: " + list_msgs[i].NOME;
            let mensagem = document.createElement("p");
            mensagem.innerHTML = "MENSAGEM: " + list_msgs[i].MENSAGEM;

            new_msg.appendChild(h);
            new_msg.appendChild(remetente);
            new_msg.appendChild(nome);
            new_msg.appendChild(mensagem);

            mensagens.appendChild(new_msg);
        }

    } else {

        let mensagens = document.getElementById("mensagens2");
        let h = document.createElement("p");
        h.innerHTML = "NAO EXISTEM MENSAGENS PARA ESTE ANUNCIO";
        mensagens.appendChild(h);
    }
}

//LISTAR ANUNCIOS INATIVOS
function listinativos() {

    list_inativos = JSON.parse(list_inativos);

    //LISTA OS ANUNCIOS ATIVOS E O DETALHAMENTO
    if (list_inativos.length > 0) {
        //CALCULO DO N DE PAGS NECESSÁRIAS
        var obj = paginacao(list_inativos, 0, 4);
        var n_pags = obj.pags;

        //CRIAR N DE BOTOES CONSOANTE PAGS NECESSÁRIAS
        for (var i = 0; i < n_pags; i++) {
            var b = document.createElement("button");
            b.innerHTML = i + 1;
            b.classList.add("b_at");
            butoes1.appendChild(b);
        }


        $(".b_at").click(function(event) {
            event.preventDefault();
            var n = jQuery(this).text();
            var obj1 = paginacao(list_inativos, n, 4);
            let anuncios = document.getElementById("anun_inativos");

            while (anuncios.firstChild) {
                anuncios.removeChild(anuncios.firstChild);
            }

            for (var i = 0; i < obj1.obj.length; i++) {
                var anuncio = document.createElement("div");
                anuncio.classList.add("anuncio");

                let h = document.createElement("h3");
                let aid = document.createElement("p");
                let preco = document.createElement("p");
                let zona = document.createElement("p");
                aid.classList.add("aid");
                aid.setAttribute("id", i);

                h.innerHTML = "ANUNCIO:";
                aid.innerHTML = "Aid:" + obj1.obj[i].AID;
                preco.innerHTML = "Preco: " + obj1.obj[i].PRECO;
                zona.innerHTML = "Zona: " + obj1.obj[i].ZONA;

                anuncio.appendChild(h);
                anuncio.appendChild(aid);
                anuncio.appendChild(preco);
                anuncio.appendChild(zona);

                anuncios.appendChild(anuncio);
            }

            $(".aid").click(function(event) {
                event.preventDefault();

                let destaque = document.getElementById("lista_anuncios");
                let but = document.getElementById("butoes1");
                but.innerHTML = "";
                destaque.innerHTML = "";

                var anuncio3 = document.createElement("div");
                anuncio3.setAttribute("id", "anuncio1");
                var index = event.target.id;
                anuncio3.innerHTML = ""


                let h = document.createElement("h3");
                let aid2 = document.createElement("p");
                let tipo_alojamento = document.createElement("p");
                let preco2 = document.createElement("p");
                let genero = document.createElement("p");
                let zona2 = document.createElement("p");
                let detalhes = document.createElement("p");
                let anunciante = document.createElement("p");
                let contacto = document.createElement("p");
                let tipo2 = document.createElement("p");

                h.innerHTML = "ANUNCIO:";

                aid2.innerHTML = "Aid:" + obj1.obj[index].AID;
                anunciante.innerHTML = "Anunciante: " + obj1.obj[index].USER_NAME;
                tipo_alojamento.innerHTML = "Tipo alojamento: " + obj1.obj[index].TIPO_ALOJAMENTO;
                preco2.innerHTML = "Preco: " + obj1.obj[index].PRECO;
                genero.innerHTML = "Genero: " + obj1.obj[index].GENERO;
                zona2.innerHTML = "Zona: " + obj1.obj[index].ZONA;
                detalhes.innerHTML = "Detalhes: " + obj1.obj[index].DETALHES;
                contacto.innerHTML = "Contacto: " + obj1.obj[index].CONTACTO;
                tipo2.innerHTML = "Tipo: " + obj1.obj[index].TIPO;

                anuncio3.appendChild(h);
                anuncio3.appendChild(aid2);
                anuncio3.appendChild(tipo2);
                anuncio3.appendChild(tipo_alojamento);
                anuncio3.appendChild(preco2);
                anuncio3.appendChild(genero);
                anuncio3.appendChild(zona2);
                anuncio3.appendChild(anunciante);
                anuncio3.appendChild(detalhes);
                anuncio3.appendChild(contacto);


                let form = document.createElement("form");
                form.setAttribute("method", "post");
                form.setAttribute("id", "form");
                form.setAttribute("action", "/anuncio_alterado");

                // ENVIO DO AID ATRAVÉS DO FORM
                let aid3 = document.createElement("input");
                aid3.setAttribute("type", "hidden");
                aid3.setAttribute("name", "aid");
                aid3.setAttribute("value", obj1.obj[index].AID);

                // ENVIO DO NOVO ESTADO ATRAVÉS DO FORM
                let new_state = document.createElement("input");
                new_state.setAttribute("type", "hidden");
                new_state.setAttribute("name", "estado");
                new_state.setAttribute("value", "ativo");

                let s = document.createElement("input");
                s.setAttribute("type", "submit");
                s.setAttribute("id", "button");
                s.setAttribute("value", "Ativar");

                let csrf_token = document.createElement("input");
                csrf_token.setAttribute("type", "hidden");
                csrf_token.setAttribute("name", "_csrf");
                csrf_token.setAttribute("value", token);


                form.appendChild(aid3);
                form.appendChild(new_state);
                form.appendChild(s);
                form.appendChild(csrf_token);

                anuncio3.appendChild(form);

                destaque.appendChild(anuncio3);

            });

        });

    }

}

//LISTAR ANUNCIOS ATIVOS
function listativos() {
    list_ativos = JSON.parse(list_ativos);

    //LISTA OS ANUNCIOS ATIVOS E O DETALHAMENTO
    if (list_ativos.length > 0) {
        //CALCULO DO N DE PAGS NECESSÁRIAS
        var obj = paginacao(list_ativos, 0, 4);
        var n_pags = obj.pags;

        //CRIAR N DE BOTOES CONSOANTE PAGS NECESSÁRIAS
        for (var i = 0; i < n_pags; i++) {
            var b = document.createElement("button");
            b.innerHTML = i + 1;
            b.classList.add("b_at2");
            butoes2.appendChild(b);
        }


        $(".b_at2").click(function(event) {
            event.preventDefault();
            var n = jQuery(this).text();
            var obj1 = paginacao(list_ativos, n, 4);
            let anuncios = document.getElementById("anun_ativos");

            while (anuncios.firstChild) {
                anuncios.removeChild(anuncios.firstChild);
            }

            for (var i = 0; i < obj1.obj.length; i++) {
                var anuncio = document.createElement("div");
                anuncio.classList.add("anuncio2");

                let h = document.createElement("h3");
                let aid = document.createElement("p");
                let preco = document.createElement("p");
                let zona = document.createElement("p");
                aid.classList.add("aid1");
                aid.setAttribute("id", i);

                h.innerHTML = "ANUNCIO:";
                aid.innerHTML = "Aid:" + obj1.obj[i].AID;
                preco.innerHTML = "Preco: " + obj1.obj[i].PRECO;
                zona.innerHTML = "Zona: " + obj1.obj[i].ZONA;

                anuncio.appendChild(h);
                anuncio.appendChild(aid);
                anuncio.appendChild(preco);
                anuncio.appendChild(zona);

                anuncios.appendChild(anuncio);
            }

            $(".aid1").click(function(event) {
                event.preventDefault();

                let destaque = document.getElementById("lista_anuncios");
                let but = document.getElementById("butoes2");
                but.innerHTML = "";
                destaque.innerHTML = "";

                var anuncio3 = document.createElement("div");
                anuncio3.setAttribute("id", "anuncio1");
                var index = event.target.id;
                anuncio3.innerHTML = ""


                let h = document.createElement("h3");
                let aid2 = document.createElement("p");
                let tipo_alojamento = document.createElement("p");
                let preco2 = document.createElement("p");
                let genero = document.createElement("p");
                let zona2 = document.createElement("p");
                let detalhes = document.createElement("p");
                let anunciante = document.createElement("p");
                let contacto = document.createElement("p");
                let tipo2 = document.createElement("p");

                h.innerHTML = "ANUNCIO:";

                aid2.innerHTML = "Aid:" + obj1.obj[index].AID;
                anunciante.innerHTML = "Anunciante: " + obj1.obj[index].USER_NAME;
                tipo_alojamento.innerHTML = "Tipo alojamento: " + obj1.obj[index].TIPO_ALOJAMENTO;
                preco2.innerHTML = "Preco: " + obj1.obj[index].PRECO;
                genero.innerHTML = "Genero: " + obj1.obj[index].GENERO;
                zona2.innerHTML = "Zona: " + obj1.obj[index].ZONA;
                detalhes.innerHTML = "Detalhes: " + obj1.obj[index].DETALHES;
                contacto.innerHTML = "Contacto: " + obj1.obj[index].CONTACTO;
                tipo2.innerHTML = "Tipo: " + obj1.obj[index].TIPO;

                anuncio3.appendChild(h);
                anuncio3.appendChild(aid2);
                anuncio3.appendChild(tipo2);
                anuncio3.appendChild(tipo_alojamento);
                anuncio3.appendChild(preco2);
                anuncio3.appendChild(genero);
                anuncio3.appendChild(zona2);
                anuncio3.appendChild(anunciante);
                anuncio3.appendChild(detalhes);
                anuncio3.appendChild(contacto);

                destaque.appendChild(anuncio3);

                let form = document.createElement("form");
                form.setAttribute("method", "post");
                form.setAttribute("id", "form");
                form.setAttribute("action", "/anuncio_alterado");

                // ENVIO DO AID ATRAVÉS DO FORM
                let aid3 = document.createElement("input");
                aid3.setAttribute("type", "hidden");
                aid3.setAttribute("name", "aid");
                aid3.setAttribute("value", obj1.obj[index].AID);

                // ENVIO DO NOVO ESTADO ATRAVÉS DO FORM
                let new_state = document.createElement("input");
                new_state.setAttribute("type", "hidden");
                new_state.setAttribute("name", "estado");
                new_state.setAttribute("value", "inativo");

                let s = document.createElement("input");
                s.setAttribute("type", "submit");
                s.setAttribute("id", "button");
                s.setAttribute("value", "Desativar");

                let csrf_token = document.createElement("input");
                csrf_token.setAttribute("type", "hidden");
                csrf_token.setAttribute("name", "_csrf");
                csrf_token.setAttribute("value", token);


                form.appendChild(aid3);
                form.appendChild(new_state);
                form.appendChild(s);
                form.appendChild(csrf_token);


                anuncio3.appendChild(form);

                destaque.appendChild(anuncio3);

            });

        });

    }

}

function warn() {
    let h = document.createElement("h2");
    h.innerHTML = "Anuncio " + tex;
    let div = document.getElementById("text");
    div.appendChild(h);
}

function getMb() {
    var geting = $.get("http://alunos.di.uevora.pt/tweb/t2/mbref4payment", "amount=10");

    //PERCORRE O OBJETO RETORNADO COM AS MENSAGENS E COLOCA NA DIV
    geting.done(function(data) {
        let div = document.getElementById("pagamento");
        let h = document.createElement("h3");
        h.innerHTML = "Pagamento multibanco";

        let entidade = document.createElement("p");
        let referencia = document.createElement("p");
        let valor = document.createElement("p");

        entidade.innerHTML = "Entidade: " + data.mb_entity;
        referencia.innerHTML = "Referencia: " + data.mb_reference;
        valor.innerHTML = "Valor: " + data.mb_amount + "&euro;";

        div.appendChild(h);
        div.appendChild(entidade);
        div.appendChild(referencia);
        div.appendChild(valor);
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