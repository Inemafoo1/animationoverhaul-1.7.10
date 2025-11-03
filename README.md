# Animation Overhaul 1.7.10 Edition (GitHub Ready)

Projeto completo do mod **Animation Overhaul 1.7.10 Edition**, configurado para build automático via GitHub Actions.
Baseado no ExampleMod 1.7.10 (GTNewHorizons) + código-fonte gerado anteriormente.

## Estrutura
- `src/main/java` — código Java do mod
- `src/main/resources` — assets e animações
- `build.gradle` — configuração ForgeGradle 1.2
- `gradlew` + `gradlew.bat` + `gradle/wrapper` — wrapper Gradle
- `.github/workflows/build.yml` — workflow para build automático

## Como usar
1. Crie um novo repositório no GitHub (por exemplo, `animationoverhaul-1.7.10`).
2. Faça upload deste ZIP no repositório (ou use `git init`, `git add .`, `git commit`, `git push`).
3. Vá até a aba **Actions** do seu repositório e aguarde o workflow executar.
4. O artefato `.jar` será disponibilizado para download na aba **Actions → Artifacts**.

## Build manual (local)
Para compilar manualmente:
```bash
gradlew setupDecompWorkspace --refresh-dependencies
gradlew build
```

O `.jar` será gerado em `build/libs/animationoverhaul17-1.0.0.jar`.
