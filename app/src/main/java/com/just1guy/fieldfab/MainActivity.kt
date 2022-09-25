package com.just1guy.fieldfab

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just1guy.fieldfab.appstate.*
import com.just1guy.fieldfab.datapersistence.DuctData
import com.just1guy.fieldfab.opengl.DuctRenderView
import com.just1guy.fieldfab.pages.*
import com.just1guy.fieldfab.ui.theme.FieldFabTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

//import org.reduxkotlin.Store
//import org.reduxkotlin.createThreadSafeStore

@Composable
fun TopBar(kState: KotlinDefaultAppState, title: String) {
    when (kState.navController.currentDestination?.route) {
        "main-menu" -> TopAppBar(
            title = { Text(title) },
            actions = {
                IconButton(onClick = { kState.navController.navigate(route = "settings-page") }) {
                    Icon(Icons.Filled.Settings, contentDescription = null)
                }
            }
        )
        "settings-page" -> TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = {
                    kState.navController.navigateUp()
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
        )
    }
}

class MainActivity : ComponentActivity() {
	companion object {
		private val charMap = mapOf<String, String>(" " to "%20","!" to "%21","\"" to "%22","#" to "%23","$" to "%24","%" to "%25","&" to "%26","'" to "%27","(" to "%28",")" to "%29","*" to "%2A","+" to "%2B","," to "%2C","-" to "%2D","." to "%2E","/" to "%2F","0" to "%30","1" to "%31","2" to "%32","3" to "%33","4" to "%34","5" to "%35","6" to "%36","7" to "%37","8" to "%38","9" to "%39",":" to "%3A",";" to "%3B","<" to "%3C","=" to "%3D",">" to "%3E","?" to "%3F","@" to "%40","A" to "%41","B" to "%42","C" to "%43","D" to "%44","E" to "%45","F" to "%46","G" to "%47","H" to "%48","I" to "%49","J" to "%4A","K" to "%4B","L" to "%4C","M" to "%4D","N" to "%4E","O" to "%4F","P" to "%50","Q" to "%51","R" to "%52","S" to "%53","T" to "%54","U" to "%55","V" to "%56","W" to "%57","X" to "%58","Y" to "%59","Z" to "%5A","[" to "%5B","\"" to "%5C","]" to "%5D","^" to "%5E","_" to "%5F","`" to "%60","a" to "%61","b" to "%62","c" to "%63","d" to "%64","e" to "%65","f" to "%66","g" to "%67","h" to "%68","i" to "%69","j" to "%6A","k" to "%6B","l" to "%6C","m" to "%6D","n" to "%6E","o" to "%6F","p" to "%70","q" to "%71","r" to "%72","s" to "%73","t" to "%74","u" to "%75","v" to "%76","w" to "%77","x" to "%78","y" to "%79","z" to "%7A","{" to "%7B","|" to "%7C","}" to "%7D","~" to "%7E","€" to "%E2%82%AC","‚" to "%E2%80%9A","ƒ" to "%C6%92","„" to "%E2%80%9E","…" to "%E2%80%A6","†" to "%E2%80%A0","‡" to "%E2%80%A1","ˆ" to "%CB%86","‰" to "%E2%80%B0","Š" to "%C5%A0","‹" to "%E2%80%B9","Œ" to "%C5%92","Ž" to "%C5%BD","‘" to "%E2%80%98","’" to "%E2%80%99","“" to "%E2%80%9C","”" to "%E2%80%9D","•" to "%E2%80%A2","–" to "%E2%80%93","—" to "%E2%80%94","˜" to "%CB%9C","™" to "%E2%84","š" to "%C5%A1","›" to "%E2%80","œ" to "%C5%93","ž" to "%C5%BE","Ÿ" to "%C5%B8","¡" to "%C2%A1","¢" to "%C2%A2","£" to "%C2%A3","¤" to "%C2%A4","¥" to "%C2%A5","¦" to "%C2%A6","§" to "%C2%A7","¨" to "%C2%A8","©" to "%C2%A9","ª" to "%C2%AA","«" to "%C2%AB","¬" to "%C2%AC","®" to "%C2%AE","¯" to "%C2%AF","°" to "%C2%B0","±" to "%C2%B1","²" to "%C2%B2","³" to "%C2%B3","´" to "%C2%B4","µ" to "%C2%B5","¶" to "%C2%B6","·" to "%C2%B7","¸" to "%C2%B8","¹" to "%C2%B9","º" to "%C2%BA","»" to "%C2%BB","¼" to "%C2%BC","½" to "%C2%BD","¾" to "%C2%BE","¿" to "%C2%BF","À" to "%C3%80","Á" to "%C3%81","Â" to "%C3%82","Ã" to "%C3%83","Ä" to "%C3%84","Å" to "%C3%85","Æ" to "%C3%86","Ç" to "%C3%87","È" to "%C3%88","É" to "%C3%89","Ê" to "%C3%8A","Ë" to "%C3%8B","Ì" to "%C3%8C","Í" to "%C3%8D","Î" to "%C3%8E","Ï" to "%C3%8F","Ð" to "%C3%90","Ñ" to "%C3%91","Ò" to "%C3%92","Ó" to "%C3%93","Ô" to "%C3%94","Õ" to "%C3%95","Ö" to "%C3%96","×" to "%C3%97","Ø" to "%C3%98","Ù" to "%C3%99","Ú" to "%C3%9A","Û" to "%C3%9B","Ü" to "%C3%9C","Ý" to "%C3%9D","Þ" to "%C3%9E","ß" to "%C3%9F","à" to "%C3%A0","á" to "%C3%A1","â" to "%C3%A2","ã" to "%C3%A3","ä" to "%C3%A4","å" to "%C3%A5","æ" to "%C3%A6","ç" to "%C3%A7","è" to "%C3%A8","é" to "%C3%A9","ê" to "%C3%AA","ë" to "%C3%AB","ì" to "%C3%AC","í" to "%C3%AD","î" to "%C3%AE","ï" to "%C3%AF","ð" to "%C3%B0","ñ" to "%C3%B1","ò" to "%C3%B2","ó" to "%C3%B3","ô" to "%C3%B4","õ" to "%C3%B5","ö" to "%C3%B6","÷" to "%C3%B7","ø" to "%C3%B8","ù" to "%C3%B9","ú" to "%C3%BA","û" to "%C3%BB","ü" to "%C3%BC","ý" to "%C3%BD","þ" to "%C3%BE","ÿ" to "%C3%BF",)
		private val qMap = mapOf<String, String>("%20" to " ","%21" to "!","%22" to "\"","%23" to "#","%24" to "$","%25" to "%","%26" to "&","%27" to "'","%28" to "(","%29" to ")","%2A" to "*","%2B" to "+","%2C" to ",","%2D" to "-","%2E" to ".","%2F" to "/","%30" to "0","%31" to "1","%32" to "2","%33" to "3","%34" to "4","%35" to "5","%36" to "6","%37" to "7","%38" to "8","%39" to "9","%3A" to ":","%3B" to ";","%3C" to "<","%3D" to "=","%3E" to ">","%3F" to "?","%40" to "@","%41" to "A","%42" to "B","%43" to "C","%44" to "D","%45" to "E","%46" to "F","%47" to "G","%48" to "H","%49" to "I","%4A" to "J","%4B" to "K","%4C" to "L","%4D" to "M","%4E" to "N","%4F" to "O","%50" to "P","%51" to "Q","%52" to "R","%53" to "S","%54" to "T","%55" to "U","%56" to "V","%57" to "W","%58" to "X","%59" to "Y","%5A" to "Z","%5B" to "[","%5C" to "\"","%5D" to "]","%5E" to "^","%5F" to "_","%60" to "`","%61" to "a","%62" to "b","%63" to "c","%64" to "d","%65" to "e","%66" to "f","%67" to "g","%68" to "h","%69" to "i","%6A" to "j","%6B" to "k","%6C" to "l","%6D" to "m","%6E" to "n","%6F" to "o","%70" to "p","%71" to "q","%72" to "r","%73" to "s","%74" to "t","%75" to "u","%76" to "v","%77" to "w","%78" to "x","%79" to "y","%7A" to "z","%7B" to "{","%7C" to "|","%7D" to "}","%7E" to "~","%E2%82%AC" to "€","%E2%80%9A" to "‚","%C6%92" to "ƒ","%E2%80%9E" to "„","%E2%80%A6" to "…","%E2%80%A0" to "†","%E2%80%A1" to "‡","%CB%86" to "ˆ","%E2%80%B0" to "‰","%C5%A0" to "Š","%E2%80%B9" to "‹","%C5%92" to "Œ","%C5%BD" to "Ž","%E2%80%98" to "‘","%E2%80%99" to "’","%E2%80%9C" to "“","%E2%80%9D" to "”","%E2%80%A2" to "•","%E2%80%93" to "–","%E2%80%94" to "—","%CB%9C" to "˜","%E2%84" to "™","%C5%A1" to "š","%E2%80" to "›","%C5%93" to "œ","%C5%BE" to "ž","%C5%B8" to "Ÿ","%C2%A1" to "¡","%C2%A2" to "¢","%C2%A3" to "£","%C2%A4" to "¤","%C2%A5" to "¥","%C2%A6" to "¦","%C2%A7" to "§","%C2%A8" to "¨","%C2%A9" to "©","%C2%AA" to "ª","%C2%AB" to "«","%C2%AC" to "¬","%C2%AE" to "®","%C2%AF" to "¯","%C2%B0" to "°","%C2%B1" to "±","%C2%B2" to "²","%C2%B3" to "³","%C2%B4" to "´","%C2%B5" to "µ","%C2%B6" to "¶","%C2%B7" to "·","%C2%B8" to "¸","%C2%B9" to "¹","%C2%BA" to "º","%C2%BB" to "»","%C2%BC" to "¼","%C2%BD" to "½","%C2%BE" to "¾","%C2%BF" to "¿","%C3%80" to "À","%C3%81" to "Á","%C3%82" to "Â","%C3%83" to "Ã","%C3%84" to "Ä","%C3%85" to "Å","%C3%86" to "Æ","%C3%87" to "Ç","%C3%88" to "È","%C3%89" to "É","%C3%8A" to "Ê","%C3%8B" to "Ë","%C3%8C" to "Ì","%C3%8D" to "Í","%C3%8E" to "Î","%C3%8F" to "Ï","%C3%90" to "Ð","%C3%91" to "Ñ","%C3%92" to "Ò","%C3%93" to "Ó","%C3%94" to "Ô","%C3%95" to "Õ","%C3%96" to "Ö","%C3%97" to "×","%C3%98" to "Ø","%C3%99" to "Ù","%C3%9A" to "Ú","%C3%9B" to "Û","%C3%9C" to "Ü","%C3%9D" to "Ý","%C3%9E" to "Þ","%C3%9F" to "ß","%C3%A0" to "à","%C3%A1" to "á","%C3%A2" to "â","%C3%A3" to "ã","%C3%A4" to "ä","%C3%A5" to "å","%C3%A6" to "æ","%C3%A7" to "ç","%C3%A8" to "è","%C3%A9" to "é","%C3%AA" to "ê","%C3%AB" to "ë","%C3%AC" to "ì","%C3%AD" to "í","%C3%AE" to "î","%C3%AF" to "ï","%C3%B0" to "ð","%C3%B1" to "ñ","%C3%B2" to "ò","%C3%B3" to "ó","%C3%B4" to "ô","%C3%B5" to "õ","%C3%B6" to "ö","%C3%B7" to "÷","%C3%B8" to "ø","%C3%B9" to "ù","%C3%BA" to "ú","%C3%BB" to "û","%C3%BC" to "ü","%C3%BD" to "ý","%C3%BE" to "þ","%C3%BF" to "ÿ",)
		fun nameToUrl(name: String): String {
			var res = ""
			name.forEach { if (charMap.containsKey(it.toString())) res += charMap[it.toString()] else res += it }
			return res
		}
		fun urlToName(nameUrl: String): String {
			var name = nameUrl
			qMap.forEach { kv ->
				name = name.replace(kv.key, kv.value)
			}
			return name
		}
	}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//		val action = intent?.action
//		val data = intent?.data

        setContent {
            FieldFabTheme {
                val kAppState = rememberKotlinDefaultAppState()
                val VM = remember { mutableStateOf(AppState.TEST_STATE) }
                val context = LocalContext.current
                LaunchedEffect(true) {
                    kotlin.runCatching {
                        val filesDir = context.filesDir
                        val ductsFile = File(filesDir.absolutePath, "ducts.json")
                        var obj = VM.value.ductData
                        try {
                            val serialized = String(ductsFile.readBytes())
                            obj = Json.decodeFromString(serialized)
                        } catch (_: Throwable) {
                            ductsFile.createNewFile()
                            val encoded = Json.encodeToString(obj).encodeToByteArray()
                            ductsFile.writeBytes(encoded)
                        }
                        obj
                    }.onSuccess {
                        VM.value = VM.value.copy(ductData = it)
                    }.onFailure {
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = VM.value.currentPage)},
                                navigationIcon = {
                                    if (VM.value.currentPage != "FieldFab") IconButton(onClick = {
                                        kAppState.navController.navigateUp()
                                        VM.value = VM.value.copy(currentPage = kAppState.navController.currentBackStackEntry?.destination?.route ?: "FieldFab")
                                    })
									{
                                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                                    }
                                },
                                actions = {
                                    if (
                                        listOf(
                                            "Settings",
                                            "Help",
                                            "About",
                                            "FieldFab"
                                        ).map {VM.value.currentPage != it}.reduce { acc, b -> acc && b }
                                    ) {
										IconButton(onClick = {
											val data = VM.value.currentDuct!!.data
											val name = nameToUrl(data.name)
											val url = "fieldfab://load?name=$name&width=${data.width}&length=${data.length}&depth=${data.depth}&offsetX=${data.offsetx}&offsetY=${data.offsety}&tWidth=${data.twidth}&tDepth=${data.tdepth}"
											val sendIntent = Intent().apply {
												action = Intent.ACTION_SEND
												putExtra(Intent.EXTRA_TEXT, url)
												type = "text/plain"
											}
											val shareIntent = Intent.createChooser(sendIntent, null)
											startActivity(Intent.createChooser(shareIntent, null))
										}) {
											Icon(Icons.Filled.Share, contentDescription = null)
										}
                                        IconButton(onClick = {
											val file = File(context.filesDir, "ducts.json")
											val nlist = VM.value.ductData.toMutableList()
											nlist[VM.value.currentDuctIndex!!] = VM.value.currentDuct!!.data
											VM.value = VM.value.copy(
												ductData = nlist
											)
                                            val obj = VM.value.ductData
                                            val encoded = Json.encodeToString(obj).encodeToByteArray()
											if (!file.exists()) {
												file.createNewFile()
											}
                                            if (file.canWrite()) {
												file.writeBytes(encoded)
                                            }
											return@IconButton
                                        }) {
                                            Text("Save")
                                        }
                                    }
                                    if (VM.value.currentPage != "Settings") {
										IconButton(onClick = {
											VM.value = VM.value.copy(
												currentPage = "Settings",
												lastPage = VM.value.currentPage
											)
											kAppState.navController.navigate(route = "settings-page")
										}) {
											Icon(Icons.Filled.Settings, contentDescription = null)
										}
									}
                                }
                            )
                        },
                        floatingActionButton = {
                            if (VM.value.currentPage == "FieldFab")
                            FloatingActionButton(onClick = {
                                kAppState.navController.navigate("New-Session")
                                VM.value = VM.value.copy(currentPage = "New Session")
                            }) {
                                Icon(Icons.Filled.Create, contentDescription = null)
                            } else {}
                        },
                        bottomBar = {
							if (listOf(
									"Settings",
									"Help",
									"About",
									"FieldFab"
								).map {VM.value.currentPage != it}.reduce { acc, b -> acc && b }) {
								BottomNavigation(
									backgroundColor = colorResource(id = R.color.purple_700),
									contentColor = colorResource(id = R.color.white)
								) {
									WorkingViews.values().forEach { item ->
										BottomNavigationItem(
											icon = { when (item) {
												WorkingViews.WORKSHOP -> Icon(Icons.Filled.Build, null)
												WorkingViews.THREE_D -> Icon(Icons.Filled.Refresh, null)
												WorkingViews.AR -> Icon(Icons.Filled.Face, null)
											} },
											onClick = {
												kAppState.navController.navigateUp()

												when (item) {
													WorkingViews.WORKSHOP -> {
														kAppState.navController.navigate("Duct-Editor")
													}
													WorkingViews.THREE_D -> {
														kAppState.navController.navigate("Duct-3D")
													}
													WorkingViews.AR -> TODO()
												}
											},
											label = { Text(text = when (item) {
												WorkingViews.WORKSHOP -> "Workshop"
												WorkingViews.THREE_D -> "3D"
												WorkingViews.AR -> "AR"
											}) },
											selected = false
										)
									}
								}
							}
                        }
                    ) { innerPadding ->
                        NavHost(navController = kAppState.navController, startDestination = "FieldFab", Modifier.padding(innerPadding)) {
                            composable("FieldFab") {
                                MainMenu(kAppState = kAppState, appState = VM)
                            }
                            composable("Settings-Page") {
                                SettingsPage(kAppState)
                            }
                            composable("New-Session") {
                                NewSession(kAppState = kAppState, state = VM)
                            }
                            composable("Duct-Editor") {
                                DuctEditor(kAppData = kAppState, state = VM)
                            }
							composable("Duct-3D") {
								ThreeDView(kAppData = kAppState, state = VM)
							}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivity()
}
