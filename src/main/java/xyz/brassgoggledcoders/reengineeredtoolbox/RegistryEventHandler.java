package xyz.brassgoggledcoders.reengineeredtoolbox;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import xyz.brassgoggledcoders.reengineeredtoolbox.api.ToolboxRegistries;
import xyz.brassgoggledcoders.reengineeredtoolbox.api.face.Face;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.chat.FaceSpeaker;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.core.BlankFace;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.core.EmptyFace;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.io.*;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.io.item.FaceItemInput;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.io.item.FaceItemOutput;
import xyz.brassgoggledcoders.reengineeredtoolbox.face.machine.FaceFurnace;

import static xyz.brassgoggledcoders.reengineeredtoolbox.ReEngineeredToolbox.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class RegistryEventHandler {
    @SubscribeEvent
    public static void buildFaceRegistry(RegistryEvent.NewRegistry newRegistryEvent) {
        ToolboxRegistries.FACES = new RegistryBuilder<Face>()
                .setName(new ResourceLocation(MOD_ID, "faces"))
                .setType(Face.class)
                .setDefaultKey(new ResourceLocation(MOD_ID, "empty"))
                .create();
    }

    @SubscribeEvent
    public static void registerFaces(RegistryEvent.Register<Face> faceRegisterEvent) {
        IForgeRegistry<Face> faceRegistry = faceRegisterEvent.getRegistry();

        //Core
        faceRegistry.register(new EmptyFace());
        faceRegistry.register(new BlankFace());

        //Machine
        faceRegistry.register(new FaceFurnace());

        //Input
        faceRegistry.register(new FaceItemInput());
        faceRegistry.register(new FaceItemOutput());

        faceRegistry.register(new FaceFluidInput());
        faceRegistry.register(new FaceFluidOutput());

        faceRegistry.register(new FaceEnergyInput());
        faceRegistry.register(new FaceEnergyOutput());

        //Chat
        faceRegistry.register(new FaceSpeaker());

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void textures(TextureStitchEvent.Pre textureStitchEvent) {
        ToolboxRegistries.FACES.getValues().parallelStream()
                .map(Face::getTextureLocation)
                .forEach((location) -> textureStitchEvent.getMap().registerSprite(location));
    }
}
