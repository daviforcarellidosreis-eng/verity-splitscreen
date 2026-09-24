# Verity SplitScreen

Mod Forge para Minecraft Java Edition 1.21.1 com segundo jogador local, tela dividida e sistema Verity.

## Objetivo

Criar um sistema de 2 jogadores no mesmo computador com tela dividida, separando inputs por origem e mantendo o controle do Jogador 1 intacto.

Principais requisitos atendidos na arquitetura do projeto:

- Minecraft Java Edition 1.21.1
- Forge compatível com 1.21.1
- módulo de compatibilidade específico da versão
- separação por camadas:
  - input
  - câmera
  - renderização
  - segundo jogador
  - sistema Verity
  - transformação
  - menu e UI
- Player 1 controlado por teclado e mouse
- Player 2 controlado por Xbox
- tela dividida com duas câmeras
- estados: Normal, Irritado, Monstro
- criação da base para outros ports futuros

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
│   └── ui/
│       ├── PlayerSelectionScreen.java
│       └── SecondPlayerMenuScreen.java
├── common/
│   ├── secondplayer/
│   │   ├── SecondPlayerEntity.java
│   │   ├── SecondPlayerManager.java
│   │   └── SecondPlayerState.java
│   └── verity/
│       ├── VerityMood.java
│       ├── VerityTransformationManager.java
│       └── VerityVisualState.java
├── compat/
│   └── forge1211/
│       └── Forge1211Compat.java
└── resources/
    ├── META-INF/mods.toml
    ├── pack.mcmeta
    └── assets/veritysplitscreen/lang/en_us.json
```

## Limitação real do Forge

O Forge não oferece uma API pública madura para criar "dois jogadores reais em uma única janela de cliente" exatamente como em um console. O que é possível fazer de forma prática e compatível é:

- criar um segundo jogador local real no mundo;
- separar entrada de teclado/mouse e controle Xbox;
- manipular duas câmeras e renderização simultânea;
- manter o controle do Jogador 1;
- usar uma camada específica da versão para adaptar somente o que migra entre APIs do Minecraft/Forge.

Esse projeto respeita essa arquitetura e isola os pontos de adaptação para futuras versões do jogo.

## Requisitos

- Java 21
- Gradle 8.x
- Minecraft Java 1.21.1
- Forge 52.0.0
- IntelliJ IDEA ou Eclipse

## Abrir no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Selecione `Open`.
3. Escolha a pasta do projeto.
4. Aguarde a sincronização do Gradle.
5. Configure o JDK 21.

## Abrir no Eclipse

1. Abra o Eclipse.
2. Use `Import > Existing Gradle Project`.
3. Selecione a pasta do projeto.
4. Configure o JDK 21.
5. Rode `gradlew eclipse` ou use o import do Gradle.

## Compilar

```bash
gradlew clean build
```

Se necessário:

```bash
gradle wrapper --gradle-version 8.7
./gradlew build
```

## Instalar o .jar

1. Gere o `.jar` com `gradlew build`.
2. Localize:

```text
build/libs/verity-splitscreen-0.1.0.jar
```

3. Copie para a pasta `mods` do seu Minecraft Forge 1.21.1.
4. Execute o jogo com Forge.

## Repositório

```text
https://github.com/daviforcarellidosreis-eng/verity-splitscreen
```

## Observação

A estrutura implementada aqui representa a base completa do mod e a arquitetura solicitada. A parte de renderização, câmera e input do segundo jogador foi organizada para operar no ambiente real do Forge 1.21.1, com isolamento de compatibilidade por versão para facilitar ports futuros.
