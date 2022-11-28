package dev.eggnstone.chime.observers

import com.amazonaws.services.chime.sdk.meetings.device.DeviceChangeObserver
import com.amazonaws.services.chime.sdk.meetings.device.MediaDevice
import io.flutter.plugin.common.EventChannel.EventSink
import org.json.JSONArray
import org.json.JSONObject

class ChimeDeviceChangeObserver(private val _eventSink: EventSink) : DeviceChangeObserver
{
    override fun onAudioDeviceChanged(mediaDevices: List<MediaDevice>)
    {
        val jsonObject = JSONObject()
        jsonObject.put("Name", "OnAudioDeviceChanged")
        jsonObject.put("Arguments", convertMediaDevicesToJson(mediaDevices))
        _eventSink.success(jsonObject)
    }

    private fun convertMediaDevicesToJson(mediaDevices: List<MediaDevice>): JSONArray
    {
        val list = JSONArray()

        for (mediaDevice in mediaDevices)
        {
            val item = JSONObject()
            item.put("Label", mediaDevice.label)
            item.put("Order", mediaDevice.order)
            item.put("Type", mediaDevice.type)
            list.put(item)
        }

        return list
    }
}
