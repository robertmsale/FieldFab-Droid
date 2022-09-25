val derp = "fieldfab://load?name=derp&width=69&length=420&depth=69&offsetX=69&offsetY=69&tWidth=69&tDepth=69"
print(derp.substring(16).split('&').map {it.split('=')})
