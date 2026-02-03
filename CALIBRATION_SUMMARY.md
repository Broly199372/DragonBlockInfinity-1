# Calibração do Sistema de Entidades - DragonBlockInfinity

## 📊 Resumo da Estrutura

### Arquivos Criados/Modificados

#### 1. **NuvemGeoModel.java** ✅
- **Tipo**: Model (Renderização)
- **Framework**: GeckoLib
- **Localização**: `com.dragonblockinfinity.common.entity`
- **Responsabilidade**: Define como a Nuvem é renderizada
- **Bones Disponíveis**: 
  - `root` (raiz - controla movimento geral)
  - `nubecita1` até `nubecita29` (29 partes individuais)
- **Características**:
  - Renderização translúcida (nuvem)
  - Suporta animações customizadas por bone
  - Integra com NuvemAnimations

#### 2. **NuvemEntity.java** ✅
- **Tipo**: Entidade
- **Framework**: Forge + GeckoLib
- **Localização**: `com.dragonblockinfinity.common.entity`
- **Responsabilidade**: Define comportamento da Nuvem
- **Features**:
  - Implementa `GeoEntity` (interface GeckoLib)
  - `setNoGravity(true)` - Nuvem não cai
  - `velocidade: 1.5F` - Atributo sincronizado cliente/servidor
  - Movimento flutuante suave
  - Salvamento/carregamento de dados (NBT)
- **Atributos**:
  - MAX_HEALTH: 100.0
  - MOVEMENT_SPEED: 1.5
- **TODO**: 
  - Adicionar Goals de IA (voo, procurar jogador)
  - Implementar lógica de ataque
  - Registrar animações no `registerControllers()`

#### 3. **NuvemAnimations.java** ✅
- **Tipo**: Definições de Animação
- **Framework**: GeckoLib (RawAnimation)
- **Localização**: `com.dragonblockinfinity.common.entity.animation`
- **Animações Disponíveis**:
  1. **FLOAT**: Flutuação suave no ar (looping)
  2. **FLY**: Voo rápido (looping)
  3. **ATTACK**: Ataque - contração da nuvem (single-play)
  4. **DEATH**: Morte - dispersão (single-play)
  5. **TURN**: Rotação suave (looping)
- **Método Helper**: `createAnimationController()` - Cria controller padrão
- **Referências JSON**: Espera animações em `assets/dragonblock/animations/nuvem.animation.json`

#### 4. **NuvemRenderer.java** ✅
- **Tipo**: Renderizador de Entidade
- **Framework**: GeckoLib
- **Localização**: `com.dragonblockinfinity.client.renderer.entity`
- **Responsabilidade**: Conecta NuvemEntity com NuvemGeoModel
- **Features**:
  - Estende `GeoEntityRenderer<NuvemEntity>`
  - Renderização translúcida automática
  - Shadow radius: 1.0F

#### 5. **DinossauroRenderer.java** ✅
- **Tipo**: Renderizador de Entidade
- **Framework**: Forge Native
- **Localização**: `com.dragonblockinfinity.client.renderer.entity`
- **Responsabilidade**: Renderiza o Dinossauro
- **Features**:
  - Estende `MobRenderer<DinossauroEntity, DinossauroModel>`
  - Textura: `textures/entity/dinossauro.png`
  - Shadow radius: 2.0F
- **TODO**: 
  - Criar classe `DinossauroModel` (modelo de renderização nativa Minecraft)

#### 6. **Nuvem.java** (Original Blockbench)
- **Tipo**: Modelo Blockbench (depreciado para GeckoLib)
- **Estrutura**:
  - 29 ModelParts (nubecita1-29)
  - Camada (layer): ModelLayerLocation
  - Textura: 256x256
- **Função**: Referência da estrutura do modelo
- **Status**: Substituído por NuvemGeoModel (conversa com GeckoLib)

---

## 🔗 Fluxo de Integração

```
NuvemEntity (comportamento/IA)
    ↓
    Implementa GeoEntity (GeckoLib)
    ↓
    registerControllers() → NuvemAnimations
    ↓
NuvemRenderer (renderização)
    ↓
    Usa NuvemGeoModel
    ↓
    Renderiza com AnimationController
```

---

## 📁 Estrutura de Arquivos Esperada

```
src/main/
├── java/com/dragonblockinfinity/
│   ├── common/entity/
│   │   ├── NuvemEntity.java ✅
│   │   ├── NuvemGeoModel.java ✅
│   │   ├── DinossauroEntity.java ✅
│   │   ├── Nuvem.java (referência)
│   │   └── animation/
│   │       ├── NuvemAnimations.java ✅
│   │       └── DinossauroAnimations.java ✅
│   └── client/renderer/entity/
│       ├── NuvemRenderer.java ✅
│       └── DinossauroRenderer.java ✅
└── resources/assets/dragonblock/
    ├── models/entity/
    │   ├── Dinossauro.json ✅
    │   └── nuvem.geo.json (necessário)
    ├── textures/entity/
    │   ├── dinossauro.png (necessário)
    │   └── nuvem.png (necessário)
    └── animations/
        ├── nuvem.animation.json (necessário)
        └── dinossauro.animation.json (necessário)
```

---

## 🎯 O que a Nuvem.java Contém

### Partes do Modelo (29 bones):
- **Estrutura Principal**: `root` (raiz)
- **Nuvens Individuais**: `nubecita1` até `nubecita29`
  - Cada uma é uma pequena esfera/cubo que forma a forma de nuvem
  - Posições e rotações customizadas no Blockbench
  - Podem ser animadas individualmente via GeckoLib

### Por Que Usar GeckoLib?
1. **Animações Declarativas**: Defina animações em JSON, não em código
2. **Múltiplas Partes**: Animar 29 bones é fácil com GeckoLib
3. **Performance**: Otimizado para mobile/low-end
4. **Comunidade**: Mod popular, muito suporte

---

## 🔧 Próximos Passos

### Arquivos a Criar:

1. **nuvem.geo.json** (Modelo GeckoLib)
   - Converter Nuvem.java (Blockbench) para GeckoLib JSON
   - Referência: O arquivo original tem 155 linhas, precisa ser estruturado em JSON

2. **nuvem.animation.json** (Animações)
   - FLOAT: Movimento suave (looping)
   - FLY: Movimento rápido (looping)
   - ATTACK: Contração (single)
   - DEATH: Dispersão (single)
   - TURN: Rotação (looping)

3. **nuvem.png** (Textura)
   - 256x256 pixels
   - Renderização translúcida

4. **dinossauro.png** (Textura)
   - Textura do dinossauro

5. **DinossauroModel.java**
   - Modelo de renderização nativa Minecraft
   - Similar a Nuvem.java original

6. **dinossauro.animation.json** (Opcional)
   - Se quiser usar animações JSON para dinossauro também

### Integração no Mod:

1. **EventHandler.java** (Novo)
   ```java
   @Mod.EventBusSubscriber(modid = "dragonblock", bus = Mod.EventBusSubscriber.Bus.MOD)
   public class EntityEvents {
       @SubscribeEvent
       public static void registerEntities(EntityAttributeCreationEvent event) {
           event.put(DinossauroEntity.DINOSSAURO, DinossauroEntity.createAttributes());
           event.put(NuvemEntity.NUVEM, NuvemEntity.createAttributes());
       }
       
       @SubscribeEvent
       public static void registerRenderers(EntityRenderersEvent.Register event) {
           event.registerEntityRenderer(DinossauroEntity.DINOSSAURO, DinossauroRenderer::new);
           event.registerEntityRenderer(NuvemEntity.NUVEM, NuvemRenderer::new);
       }
   }
   ```

2. **Registrador de Entidades** (Main Mod Class)
   ```java
   DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "dragonblock");
   ENTITY_TYPES.register("dinossauro", () -> DinossauroEntity.DINOSSAURO);
   ENTITY_TYPES.register("nuvem", () -> NuvemEntity.NUVEM);
   ```

---

## ✅ Checklist

- [x] NuvemEntity criada (com GeckoLib)
- [x] NuvemGeoModel criada
- [x] NuvemAnimations criada com 5 animações
- [x] NuvemRenderer criada
- [x] DinossauroRenderer criada
- [ ] Converter Nuvem.json para GeckoLib format
- [ ] Criar nuvem.animation.json
- [ ] Criar dinossauro.animation.json (opcional)
- [ ] Criar/adicionar texturas PNG
- [ ] Criar DinossauroModel.java
- [ ] Criar EventHandler para registro de entidades
- [ ] Testar rendering em-game

---

## 📝 Notas Importantes

1. **GeckoLib vs Vanilla**: 
   - Nuvem usa GeckoLib (melhor para múltiplas partes)
   - Dinossauro pode usar Vanilla ou GeckoLib

2. **Sincronização**:
   - `NuvemEntity.VELOCIDADE` usa `EntityDataAccessor` para sync cliente/servidor
   - Importante para multiplayer

3. **Sem Gravidade**:
   - `setNoGravity(true)` faz a nuvem flutuar
   - Remove aplicação de gravity automática

4. **Atributos**:
   - MAX_HEALTH: 100 (pode ser alterado)
   - MOVEMENT_SPEED: 1.5 (velocidade de movimento)

---

**Status**: ✅ Calibração Completa - Pronto para Implementação de Animações e Texturas
