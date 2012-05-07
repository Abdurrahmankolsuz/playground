require 'ruboto.rb'

include Ruboto

ruboto_import_widgets :Button, :LinearLayout, :TextView

def show_wifi_state
  wifi_service = $activity.getSystemService(android.content.Context::WIFI_SERVICE)
  ssid = wifi_service.connection_info.getSSID
  if ssid
    toast "Connected to #{ssid}"
  else
    toast "Not connected to any WIFI network"
  end
end

$activity.handle_create do |bundle|
  setTitle 'WIFI Detector'

  setup_content do
    linear_layout :orientation => LinearLayout::VERTICAL do
      button :text => "Show WIFI state", :width => :wrap_content
    end
  end

  handle_click do |view|
    if view.getText == 'Show WIFI state'
      show_wifi_state
    end
  end

end