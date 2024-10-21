package mchorse.chameleon;

import mchorse.chameleon.mclib.ValueButtons;
import mchorse.mclib.McLib;
import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.events.RegisterConfigEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Chameleon.MOD_ID, name = Chameleon.MODNAME, version = Chameleon.VERSION, dependencies = "required-after:mclib@[2.4.3,);required-after:metamorph@[1.4,)", updateJSON = "https://raw.githubusercontent.com/mchorse/chameleon/master/version.json")
public class Chameleon
{
    /* Sadly "chameleon" mod ID conflicts with another popular mod... */
    public static final String MOD_ID = "chameleon_morph";
    public static final String MODNAME = "Chameleon";
    public static final String VERSION = "1.2.2";

    @SidedProxy(serverSide = "mchorse.chameleon.CommonProxy", clientSide = "mchorse.chameleon.ClientProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        McLib.EVENT_BUS.register(this);

        proxy.preInit(event);
        logger = event.getModLog();
    }

    @SubscribeEvent
    public void onConfig(RegisterConfigEvent event)
    {
        ConfigBuilder builder = event.createBuilder("chameleon");

        /* General */
        builder.category("general").register(new ValueButtons("buttons").clientSide());
    }
}