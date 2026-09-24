# Verity SplitScreen

Mod Forge para Minecraft Java Edition 1.21.1 com suporte local a 2 jogadores em uma mesma máquina, tela dividida, entrada separada do teclado/mouse e do controle Xbox, além do sistema de transformação Verity.

## Visão geral

Este projeto foi estruturado para seguir a arquitetura modular pedida, separando em camadas:

- Controle / Input
- Câmera
- Renderização da tela dividida
- Jogador 2
- Sistema de Verity
- Transformação
- Animações e efeitos visuais
- Interface
- Compatibilidade específica da versão

A versão principal do projeto é Minecraft 1.21.1, com uma camada de compatibilidade separada para adaptar o código em futuras versões.

## Limitações reais do Forge

O Forge não oferece uma API pública e estável para "split-screen nativo de dois jogadores em um único cliente" como em um modo de console. O que existe, no Minecraft Java, é uma infraestrutura de renderização, câmera e entidades. Por razões técnicas e de compatibilidade, a solução mais funcional e próxima do objetivo é:

- criar um segundo jogador local real em mundo único;
- separar inputs por origem (teclado/mouse para Jogador 1 e controle Xbox para Jogador 2);
- dividir a tela com duas câmeras e dois pontos de vista em uma renderização customizada do cliente;
- manter o controle do Jogador 1 intacto;
- usar uma camada de compatibilidade por versão para trocar apenas os pontos de contato com o Forge.

A implementação no código foi organizada para facilitar ports e adaptações futuras.

## Estrutura do projeto

```text
src/main/java/com/verity/
├── VeritySplitMod.java
├── client/
│   ├── VerityClientEvents.java
│   ├── camera/
│   │   └── CameraController.java
│   ├── input/
│   │   ├── LocalInputManager.java
│   │   └── XboxControllerState.java
│   ├── render/
│   │   └── SplitScreenRenderer.java
│   │   └── SecondPlayerRenderer.java
│   ├── ui/
│   │   ├── PlayerSelectionScreen.java
│   │   └── SecondPlayerMenuScreen.java
│   └── verity/
│       └── VerityVisualState.java
├── common/
│   ├── secondplayer/
│   │   ├── SecondPlayerEntity.java
│   │   ├── SecondPlayerManager.java
│   │   └── SecondPlayerState.java
│   └── verity/
│       ├── VerityMood.java
│       └── VerityTransformationManager.java
├── compat/
│   └── forge1211/
│       └── Forge1211Compat.java
└── resources/
    ├── META-INF/mods.toml
    ├── pack.mcmeta
    └── assets/veritysplitscreen/lang/en_us.json
```

## Requisitos

- Java 21
- Gradle 8.x
- Minecraft Java Edition 1.21.1
- Forge 52.0.0
- IntelliJ IDEA ou Eclipse

## Abrir no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Selecione "Open".
3. Escolha a pasta do projeto.
4. Aguarde o Gradle sincronizar.
5. Se aparecerem erros de Java, configure o JDK 21.

## Abrir no Eclipse

1. Abra o Eclipse.
2. Use "Import > Existing Gradle Project".
3. Selecione a pasta do projeto.
4. Configure o JDK 21 no projeto.
5. Rode a task `gradlew eclipse` ou use o import do Gradle.

## Compilar

No diretório do projeto:

```bash
gradlew clean build
```

Se a wrapper não existir no ambiente local, gere com:

```bash
gradle wrapper --gradle-version 8.7
```

Em seguida:

```bash
./gradlew build
```

## Instalar o .jar

1. Compile o projeto.
2. Localize o arquivo:

```text
build/libs/verity-splitscreen-0.1.0.jar
```

3. Copie o `.jar` para a pasta `mods` do seu Minecraft Forge 1.21.1.
4. Inicie o launcher do Forge.

## Como o mod funciona

### Jogador 1
- Teclado + mouse
- W/A/S/D
- Espaço para pular
- Shift para agachar
- Câmera via mouse
- Controle ativo normalmente

### Jogador 2
- Controle Xbox
- START/Menu ativa o segundo jogador local
- O segundo jogador não usa outra conta do Minecraft
- Possui vida, posição, câmera e ações independentes
- Usa atributos próprios de entidade local

### Verity
- O segundo jogador pode ser configurado como `PLAYER` ou `VERITY`
- `VERITY NORMAL`: cor configurável
- `VERITY IRRITADO`: irritação crescente
- `VERITY MONSTRO`: transformação quando a irritação atingir o limiar
- Faces e expressões podem ser alteradas por estado visual

## Arquitetura multi-version

A camada `compat.forge1211` foi criada para centralizar o que muda entre versões do Forge/Minecraft. Se você for adaptar esse projeto para 1.20.1, 1.19.2 ou outra versão:

- ajuste os imports do Minecraft/Forge;
- mude o registro de entidades e renderizadores;
- revise a API de `InputEvent`, `Camera` e `RenderLevel`;
- reescreva apenas a compatibilidade da versão.

A lógica do sistema de player, split-screen e Verity deve continuar a mesma.

## Estrutura da relevante da camada compat

```java
package com.verity.compat.forge1211;

public final class Forge1211Compat {
    public static void applyVersionSpecificSetup() {
        // Ajustes específicos para 1.21.1.
    }
}
```

Isso deixa a base do projeto pronta para ports futuros sem reescrever o sistema inteiro.

## Repositório

```text
https://github.com/daviforcarellidosreis-eng/verity-splitscreen
```

## Observação final

Este projeto fornece a base arquitetural e a implementação real em Java para Forge 1.21.1, com módulos, controle separado, segunda entidade local e camada de compatibilidade. A parte de renderer de câmera em split-screen e controles Xbox exige ajustes finais de runtime em um ambiente Forge real, mas a estrutura foi montada para esse desenvolvimento de forma explícita e modular.
