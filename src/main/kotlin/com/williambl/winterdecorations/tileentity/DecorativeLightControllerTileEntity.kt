package com.williambl.winterdecorations.tileentity

import com.williambl.winterdecorations.WinterDecorations
import com.williambl.winterdecorations.block.DecorativeLightBlock
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos

class DecorativeLightControllerTileEntity : TileEntity(WinterDecorations.decorativeLightControllerTileEntityType), ITickableTileEntity {

    var timer = 0

    override fun tick() {
        if (timer % 200 == 0) {
            if (world!!.getBlockState(pos).get(BlockStateProperties.POWERED))
                enableLights()
            else
                disableLights()
            timer = 0
        }
        timer++
    }

    fun enableLights() {
        BlockPos.getAllInBox(pos.add(-32, -32, -32), pos.add(32, 32, 32)).forEach {
            val state = world!!.getBlockState(it)
            if (state.block is DecorativeLightBlock) {
                world!!.setBlockState(it, state.with(BlockStateProperties.POWERED, true))
            }
        }
    }

    fun disableLights() {
        BlockPos.getAllInBox(pos.add(-32, -32, -32), pos.add(32, 32, 32)).forEach {
            val state = world!!.getBlockState(it)
            if (state.block is DecorativeLightBlock) {
                world!!.setBlockState(it, state.with(BlockStateProperties.POWERED, false))
            }
        }
    }
}