package xyz.brassgoggledcoders.reengineeredtoolbox.api.face.capability.sided;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.brassgoggledcoders.reengineeredtoolbox.api.face.capability.single.FaceHolder;
import xyz.brassgoggledcoders.reengineeredtoolbox.api.face.capability.single.IFaceHolder;

import javax.annotation.Nullable;

public class CapabilitySidedFaceHolder {

    @CapabilityInject(ISidedFaceHolder.class)
    public static Capability<ISidedFaceHolder> SIDED_FACE_HOLDER = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(ISidedFaceHolder.class, new Capability.IStorage<ISidedFaceHolder>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<ISidedFaceHolder> capability, ISidedFaceHolder instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<ISidedFaceHolder> capability, ISidedFaceHolder instance, EnumFacing side, NBTBase nbt) {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, SidedFaceHolder::new);
    }
}
