# Android MVP 

* (English) This repository is used for demonstration purposes of the MVP Design Pattern for Android. 
* (Portuguese) Este repositório é destinado para fins de demonstração do padrão MVP em projetos Android. 

# Pre-requisites

  - Android Studio 2.3+
  - Android SDK Build Tools 23+
  - Min SDK Level: 19
 
# Preview
![Preview](http://i65.tinypic.com/1235hjl.png)

# Contact Info

- Email: alifyz@outlook.com
- Twitter: @AlifyzPires

### Android Project Features

- MVP Pattern
- AndroidX Libraries
- Material Design Widgets


License
----

MIT

Copyright - 2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


---
title: Padrão de Arquitetura MVP - Android
categories:
  - desenvolvimento
author_staff_member: alifyz
show_comments: false
---

É fato que uma boa arquitetura para um projeto de desenvolvimento pode economizar tempo, dinheiro e melhorar o processo de desenvolvimento de forma significativa, especialmente em projetos que não dependem de apenas um programador. Neste artigo, iremos abordar o padrão de arquitetura MVP (Model View Presenter) para projetos Android.

![android](https://cdn-images-1.medium.com/max/800/1*tF7mHTGDT8Nok58USfsu7g.png)

Este artigo é baseado no projeto open-source publicada pela Google em seus repositórios conhecido como Android Blueprints. Os repositórios blueprints contém padrões de arquitetura e boas praticas de desenvolvimento de software difundidas no mundo de desenvolvimento mobile como: Clean Architecture, MVVM (Model View-ViewModel), e por último MVP (Model View Presenter).

## Introdução ao Projeto: Sistema de Login

O projeto que iremos adotar as boas práticas do MVP implementa um sistema de login muito simples e com o único propósito de mostrar o contexto de implementação do MVP. Dessa forma, o sistema de login bem como a escrita do código foi pensada em ser didática e com o objetivo de demonstrar apenas o padrão MVP.

![app](https://cdn-images-1.medium.com/max/600/1*Z8jLhoeGTzaFKex4tHMOSg.png) ![app](https://cdn-images-1.medium.com/max/600/1*eO2QX2MAGoGUQrHY6NcUbw.png)

## Organização de Classes

![app](https://cdn-images-1.medium.com/max/800/1*Qkh3Uw8R6e-0qfsV_WwJ6w.png)

As classes demonstradas acima são usadas com a seguinte finalidade:

* AuthUtils: contém um login e senha válido para aplicação.
* HomeActivity: Atividade Inicial após o login do usuário.
* LoginActivity: Usada para gerenciar o fluxo de autenticação.

## Código da Login Activity

Atualmente, nosso projeto não possui nenhuma referência ao padrão MVP, e neste momento estamos gerenciando todo o fluxo de forma incorreta, fazendo toda a lógica dentro da Activity, e se continuarmos com esse padrão iremos acabar com uma classe gigante e possivelmente com milhares de linhas, cheias de regras de negócio e códigos que atrapalham a extensão e qualidade do projeto, fazendo com que seja difícil criar testes de unidade ou até mesmo de interface.

![app](http://i64.tinypic.com/2gue07b.png)

Repare que nossa Activity implementa toda a lógica de login (simplificada para fins de demonstração), e com isso nossa classe teve que implementar a regra de negócio que valida o login do usuário.

O nosso objetivo principal é deixar de manipular regras de negócio dentro da View (Activity e Fragments), e criarmos nossas validações dentro do nosso presenter, que deverá ser o responsável por toda a lógica de nossa aplicação.

O fluxo básico do padrão de arquitetura MVP pode ser demonstrado com a imagem a seguir:
	
![app](https://cdn-images-1.medium.com/max/800/1*TuWeZzR14MmB-RBbjtZl-A.png)

No padrão MVP, a View (Activity ou Fragment) comunica com o Presenter a respeito dos eventos que estão ocorrendo na interface gráfica como cliques, cliques longos, e demais tipos de input do sistema Android, e se necessário envia informações por meio de parâmetros para que o Presenter possa tomar medidas necessárias de acordo com as regras de negócio.

Nesse sentido, a View se torna passiva pois ela não é responsável por atualizar a interface por conta própria, afinal ela recebe instruções do Presenter para exibir alguma atualização na interface.

## Implementação do MVP
Para implementarmos o padrão MVP é necessário criarmos algumas interfaces que serão implementadas pelas camadas do Presenter e da View. Com estas interfaces podemos definir métodos que são comuns a todas as classes do padrão MVP.

Em nosso exemplo, as interfaces básicas serão criadas dentro do pacote: MVP

![app](https://cdn-images-1.medium.com/max/800/1*fbIao7SFb84DudJzYSs3bA.png)

A seguir você pode conferir como cada uma dessas interfaces foi criada para fins de implementação do MVP.

![app](http://i65.tinypic.com/29ehbx0.png)

As duas interfaces acima serão a planta baixa para implementação de qualquer View e ou Presenter em nossa aplicação. O método bindViews() foi definido no BaseView porque uma das atividades mais comuns que qualquer programador Android faz no momento de criar uma nova View (Activity ou Fragment) é criar os objetos que representam elementos na interface de usuário como botões, EditText, ImageView e outros, e chamar logo em seguida o método findViewById dentro da Activity.

Dessa forma, sempre que implementarmos essa interface iremos realizar o bind das views dentro do método bindViews().

## Definição do Contrato de Arquitetura

![app](https://cdn-images-1.medium.com/max/800/1*1GWo2PTBbo2mgIEGqxdC7w.jpeg)

Agora que temos nossa planta baixa da arquitetura pronta para implementação vamos criar o nossa classe contrato.

O contrato no padrão MVP serve como uma maneira de declarar todos os métodos que devem ser implementados pela camada View e Presenter, assim temos um acordo por meio de interfaces que simplifica o processo de desenvolvimento e extensão de uma nova funcionalidade ou regra de negócio.

A principal vantagem de usar interfaces e que podemos definir a arquitetura do sistema e implementar em uma classe concreta posteriormente, fazendo com que a extensão seja facilitada para implementar lógicas diferentes a medida que o contexto da aplicação muda.

Veja como ficou a implementação de nosso Contrato:

![app](http://i65.tinypic.com/2sakprp.png)

Agora que temos nosso contrato preparado podemos implementar a lógica necessária dentro de nosso presenter. Para isso, criamos uma classe concreta como o nome LoginPresenter que implementa a interface definida dentro do nosso contrato LoginContract.Presenter.

O padrão de definição de interfaces e contratos conforme demonstrado acima é o principal design pattern definido nos repositórios de arquitetura da Google, portanto é extremamente recomendável que você o siga para fins implementação de melhores práticas.

Por exemplo, caso seja necessário implementar um novo contrato para outra Atividade (UserActivity) você precisa definir um novo contrato com a seguinte nomenclatura UserContract, e o Presenter seguindo o mesmo padrão que deverá ser chamado de UserPresenter, conforme já demonstrado acima.


## Implementação do Presenter
Com o nosso contrato pronto para implementação, vamos agora implementar o nosso presenter que será o principal responsável pela camada de lógica da aplicação.

Em nosso exemplo, toda a lógica de login foi simplificada conforme abaixo:

![app](http://i64.tinypic.com/27y4srb.png)

## Implementação da View na Activity de Login
Agora só precisamos implementar a última camada para finalizarmos a completa implementação do padrão MVP.

Devemos então implementar a interface definida em nosso contrato de arquitetura e realizar um bind da nossa camada View com o Presenter. O recomendável nessa situação é fazer a instanciação do objeto Presenter por meio do lazy instantiation do Kotlin e no método onCreate finalizar sua criação.

![app](http://i65.tinypic.com/hv5d2e.jpg)

No método onCreate da Activity devemos então finalizar o bind do Presenter com a camada View do MVP.

![app](http://i65.tinypic.com/wv9ueo.png)

Agora só precisamos implementar os métodos definidos pelo contrato.

![app](http://i67.tinypic.com/flc5d2.png)

Com um pouco de refactoring podemos mover algumas partes do código para seus respectivos métodos e implementar os restantes.

Confira como ficou a nossa Activity com o padrão MVP aplicado.

![app](http://i66.tinypic.com/hwhdzp.jpg)

Agora em nossa Activity os métodos criam uma separação clara de responsabilidade, implementando apenas suas respectivas funções e assim tornando o código-fonte mais legível e fácil de manter.

## Conclusão
Agora que temos uma completa visão do padrão MVP podemos constatar que o código de nossa Activity ficou mais simples de ler e compreender, além da facilidade de extensão. Se precisarmos implementar uma nova funcionalidade ou método novo basta definir na interface LoginContract e implementar em suas respectivas camadas.

Espero que tenham gostado!

Dúvidas ou feedback, estou à disposição!


