% ler_input(-Jogadas:list, +Arquivo:string) is det
% Jogadas é uma lista de pares de jogadas, cada um representando uma jogada de cada jogador no formato (Jogador, Jogada).
% Arquivo é o nome do arquivo de jogadas.
ler_input(Jogadas, Arquivo) :-
% associar o arquivo ao input padrão
see(Arquivo),
% ler a primeira linha
gets(Linha),
% dividir a linha em duas jogadas
atomic_list_concat([J1, J2], " ", Linha),
% adicionar as jogadas à lista
ler_input(Jogadas, J1, J2),
% fechar o arquivo
seen.

% ler_input(-Jogadas:list, +J1:string, +J2:string) is det
% Jogadas é uma lista de pares de jogadas, cada um representando uma jogada de cada jogador no formato (Jogador, Jogada).
% J1 e J2 são as últimas jogadas lidas.
ler_input(Jogadas, J1, J2) :-
    % ler a próxima linha
    gets(Linha),
    % se for o final do arquivo, retornar a lista de jogadas
    ( Linha = end_of_file -> Jogadas = [(b, J1), (p, J2)]
    % senão, dividir a linha em duas jogadas e adicioná-las à lista
    ; atomic_list_concat([J3, J4], " ", Linha), ler_input(Rest, J3, J4), Jogadas = [(b, J1), (p, J2)|Rest]
    ).



ler_input(Jogadas,"jogo-1.txt").
writeln(Jogadas).