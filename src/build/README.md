# Build instructions - Animation Overhaul 1.7.10 Edition

## Requisitos
- JDK 7 (ou compatível com o seu MCP/Forge 1.7.10 setup)
- Forge 1.7.10 MDK / MCP workspace (siga o guia de 1.7.10)
- (Opcional) IDE: Eclipse ou IntelliJ IDEA

## Passos (resumo)
1. Baixe e configure o Forge MDK 1.7.10 com MCP conforme guias clássicos.
2. Importe este diretório `AnimationOverhaul-1.7.10-src` como projeto Java no workspace do Forge/MCP.
3. Ajuste paths do source (alguns setups antigos esperam `src` em posições diferentes).
4. Compile usando o sistema do seu workspace (ant/gradle conforme seu MDK).
5. Gere o .jar e coloque em `%APPDATA%/.minecraft/mods/` (Windows) ou `~/.minecraft/mods/`.
6. Execute Minecraft com Forge 1.7.10 e verifique logs para erros de reflexão.
7. Ajuste `config/animationoverhaul.cfg` para calibrar intensidades.

## Observações
- Este projeto é um *starter* que simula as animações via manipulação de ModelBiped. Em alguns ambientes, campos podem estar ofuscados com nomes diferentes — verifique logs e adapte `HookRenderPlayer` (campo modelBipedMain) se necessário.
- Não é possível compilar neste ambiente; você deve compilar localmente.
