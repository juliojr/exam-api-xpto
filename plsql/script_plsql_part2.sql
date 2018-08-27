-- A) Uma consulta onde mostre os 10 primeiros produtos.
SELECT
    idproduto,
    codigointerno,
    descr,
    ativo
FROM
    produto
WHERE
    ROWNUM <= 10;

-- B) Uma consulta onde mostre os produtos apenas com embalagens ativas.

SELECT DISTINCT
    p.*
FROM
    produto p,
    embalagem e
WHERE
    p.idproduto = e.idproduto (+)
    AND e.ativo = 'S';

-- C) Uma consulta que traga quantidade de embalagens de cada produto.

SELECT
    p.idproduto,
    p.descr,
    COUNT(*) AS qtde_embalagens
FROM
    produto p,
    embalagem e
WHERE
    p.idproduto = e.idproduto (+)
GROUP BY
    p.idproduto,
    p.descr
ORDER BY
    p.idproduto;

-- D) Insira um novo produto e uma nova embalagem para esse produto de acordo com a estrutura dados

INSERT INTO produto (
    codigointerno,
    descr,
    ativo
) VALUES (
    '000006',
    'CREME DENTAL GOLCATE',
    'S'
);

INSERT INTO embalagem (
    idproduto,
    barra,
    descr,
    fatorconversao,
    altura,
    largura,
    comprimento,
    ativo
) VALUES (
    (
        SELECT
            idproduto
        FROM
            produto
        WHERE
            codigointerno = '000006'
    ),
    '789100079955',
    'CREME DENTAL GOLCATE UN',
    1,
    60,
    60,
    500,
    'S'
);

 -- E) Altere a altura para 250, largura para 120 e comprimento para 150 das embalagens dos produtos cujo FATORCONVERSAO seja igual a 1.

UPDATE embalagem
SET
    altura = 250,
    largura = 120,
    comprimento = 150
WHERE
    fatorconversao = 1;