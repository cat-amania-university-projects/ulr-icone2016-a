/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module_traitement;
import java.io.IOException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import org.elasticsearch.action.search.*;
import org.elasticsearch.action.get.*;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 *
 * @author Alberto
 */
public class indexation {
    public static void main(String[] args) throws IOException {

		run();
    }
    
    
	public static void run() throws IOException {
            
    Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
    TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
        
     IndexResponse response = client.prepareIndex("recherche1", "pageweb", "1")
     .setSource(putJsonDocument("Une introduction à PHP",
                                "Dans cette section, nous voulons illustrer les principes de base de PHP dans une courte introduction. Ce chapitre traite uniquement de création de pages web dynamiques avec PHP, laissant de coté temporairement les autres possibilités de PHP. Voyez la section Ce que peut faire PHP pour plus d'informations. ",
                                "http://php.net/manual/fr/tutorial.php",
                                2,
                                "PHP",
                                "<!DOCTYPE html>\n" +
                            "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"fr\">\n" +
                            "<head>\n" +
                            "\n" +
                            "  <meta charset=\"utf-8\">\n" +
                            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> \n" +
                            "\n" +
                            "  <title>PHP: Une introduction &agrave; PHP - Manual </title>\n" +
                            "\n" +
                            " <link rel=\"shortcut icon\" href=\"http://php.net/favicon.ico\">\n" +
                            " <link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"http://php.net/phpnetimprovedsearch.src\" title=\"Add PHP.net search\">\n" +
                            " <link rel=\"alternate\" type=\"application/atom+xml\" href=\"http://php.net/releases/feed.php\" title=\"PHP Release feed\">\n" +
                            " <link rel=\"alternate\" type=\"application/atom+xml\" href=\"http://php.net/feed.atom\" title=\"PHP: Hypertext Preprocessor\">\n" +
                            "\n" +
                            " <link rel=\"canonical\" href=\"http://php.net/manual/fr/tutorial.php\">\n" +
                            " <link rel=\"shorturl\" href=\"http://php.net/manual/fr/tutorial.php\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/fr/tutorial.php\" hreflang=\"x-default\">\n" +
                            "\n" +
                            " <link rel=\"contents\" href=\"http://php.net/manual/fr/index.php\">\n" +
                            " <link rel=\"index\" href=\"http://php.net/manual/fr/getting-started.php\">\n" +
                            " <link rel=\"prev\" href=\"http://php.net/manual/fr/intro-whatcando.php\">\n" +
                            " <link rel=\"next\" href=\"http://php.net/manual/fr/tutorial.requirements.php\">\n" +
                            "\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/en/tutorial.php\" hreflang=\"en\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/pt_BR/tutorial.php\" hreflang=\"pt_BR\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/zh/tutorial.php\" hreflang=\"zh\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/fr/tutorial.php\" hreflang=\"fr\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/de/tutorial.php\" hreflang=\"de\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/ja/tutorial.php\" hreflang=\"ja\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/kr/tutorial.php\" hreflang=\"kr\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/ro/tutorial.php\" hreflang=\"ro\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/ru/tutorial.php\" hreflang=\"ru\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/es/tutorial.php\" hreflang=\"es\">\n" +
                            " <link rel=\"alternate\" href=\"http://php.net/manual/tr/tutorial.php\" hreflang=\"tr\">\n" +
                            "\n" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://php.net/cached.php?t=1421837618&amp;f=/fonts/Fira/fira.css\" media=\"screen\">\n" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://php.net/cached.php?t=1421837618&amp;f=/fonts/Font-Awesome/css/fontello.css\" media=\"screen\">\n" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://php.net/cached.php?t=1478800802&amp;f=/styles/theme-base.css\" media=\"screen\">\n" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://php.net/cached.php?t=1449787206&amp;f=/styles/theme-medium.css\" media=\"screen\">\n" +
                            "\n" +
                            " <!--[if lte IE 7]>\n" +
                            " <link rel=\"stylesheet\" type=\"text/css\" href=\"http://php.net/styles/workarounds.ie7.css\" media=\"screen\">\n" +
                            " <![endif]-->\n" +
                            "\n" +
                            " <!--[if lte IE 8]>\n" +
                            " <script type=\"text/javascript\">\n" +
                            "  window.brokenIE = true;\n" +
                            " </script>\n" +
                            " <![endif]-->\n" +
                            "\n" +
                            " <!--[if lte IE 9]>\n" +
                            " <link rel=\"stylesheet\" type=\"text/css\" href=\"http://php.net/styles/workarounds.ie9.css\" media=\"screen\">\n" +
                            " <![endif]-->\n" +
                            "\n" +
                            " <!--[if IE]>\n" +
                            " <script type=\"text/javascript\" src=\"http://php.net/js/ext/html5.js\"></script>\n" +
                            " <![endif]-->\n" +
                            "\n" +
                            " <base href=\"http://php.net/manual/fr/tutorial.php\">\n" +
                            "\n" +
                            "</head>\n" +
                            "<body class=\"docs \">\n" +
                            "\n" +
                            "<nav id=\"head-nav\" class=\"navbar navbar-fixed-top\">\n" +
                            "  <div class=\"navbar-inner clearfix\">\n" +
                            "    <a href=\"/\" class=\"brand\"><img src=\"/images/logo.php\" width=\"48\" height=\"24\" alt=\"php\"></a>\n" +
                            "    <div id=\"mainmenu-toggle-overlay\"></div>\n" +
                            "    <input type=\"checkbox\" id=\"mainmenu-toggle\">\n" +
                            "    <ul class=\"nav\">\n" +
                            "      <li class=\"\"><a href=\"/downloads\">Downloads</a></li>\n" +
                            "      <li class=\"active\"><a href=\"/docs.php\">Documentation</a></li>\n" +
                            "      <li class=\"\"><a href=\"/get-involved\" >Get Involved</a></li>\n" +
                            "      <li class=\"\"><a href=\"/support\">Help</a></li>\n" +
                            "    </ul>\n" +
                            "    <form class=\"navbar-search\" id=\"topsearch\" action=\"/search.php\">\n" +
                            "      <input type=\"hidden\" name=\"show\" value=\"quickref\">\n" +
                            "      <input type=\"search\" name=\"pattern\" class=\"search-query\" placeholder=\"Search\" accesskey=\"s\">\n" +
                            "    </form>\n" +
                            "  </div>\n" +
                            "  <div id=\"flash-message\"></div>\n" +
                            "</nav>\n" +
                            "<div class=\"headsup\"><a href='/index.php#id2016-12-08-2'>PHP 5.6.29 Released</a></div>\n" +
                            "<nav id=\"trick\"><div><dl>\n" +
                            "<dt><a href='/manual/en/getting-started.php'>Getting Started</a></dt>\n" +
                            "	<dd><a href='/manual/en/introduction.php'>Introduction</a></dd>\n" +
                            "	<dd><a href='/manual/en/tutorial.php'>A simple tutorial</a></dd>\n" +
                            "<dt><a href='/manual/en/langref.php'>Language Reference</a></dt>\n" +
                            "	<dd><a href='/manual/en/language.basic-syntax.php'>Basic syntax</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.types.php'>Types</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.variables.php'>Variables</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.constants.php'>Constants</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.expressions.php'>Expressions</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.operators.php'>Operators</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.control-structures.php'>Control Structures</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.functions.php'>Functions</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.oop5.php'>Classes and Objects</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.namespaces.php'>Namespaces</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.errors.php'>Errors</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.exceptions.php'>Exceptions</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.generators.php'>Generators</a></dd>\n" +
                            "	<dd><a href='/manual/en/language.references.php'>References Explained</a></dd>\n" +
                            "	<dd><a href='/manual/en/reserved.variables.php'>Predefined Variables</a></dd>\n" +
                            "	<dd><a href='/manual/en/reserved.exceptions.php'>Predefined Exceptions</a></dd>\n" +
                            "	<dd><a href='/manual/en/reserved.interfaces.php'>Predefined Interfaces and Classes</a></dd>\n" +
                            "	<dd><a href='/manual/en/context.php'>Context options and parameters</a></dd>\n" +
                            "	<dd><a href='/manual/en/wrappers.php'>Supported Protocols and Wrappers</a></dd>\n" +
                            "</dl>\n" +
                            "<dl>\n" +
                            "<dt><a href='/manual/en/security.php'>Security</a></dt>\n" +
                            "	<dd><a href='/manual/en/security.intro.php'>Introduction</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.general.php'>General considerations</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.cgi-bin.php'>Installed as CGI binary</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.apache.php'>Installed as an Apache module</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.sessions.php'>Session Security</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.filesystem.php'>Filesystem Security</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.database.php'>Database Security</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.errors.php'>Error Reporting</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.globals.php'>Using Register Globals</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.variables.php'>User Submitted Data</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.magicquotes.php'>Magic Quotes</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.hiding.php'>Hiding PHP</a></dd>\n" +
                            "	<dd><a href='/manual/en/security.current.php'>Keeping Current</a></dd>\n" +
                            "<dt><a href='/manual/en/features.php'>Features</a></dt>\n" +
                            "	<dd><a href='/manual/en/features.http-auth.php'>HTTP authentication with PHP</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.cookies.php'>Cookies</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.sessions.php'>Sessions</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.xforms.php'>Dealing with XForms</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.file-upload.php'>Handling file uploads</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.remote-files.php'>Using remote files</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.connection-handling.php'>Connection handling</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.persistent-connections.php'>Persistent Database Connections</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.safe-mode.php'>Safe Mode</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.commandline.php'>Command line usage</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.gc.php'>Garbage Collection</a></dd>\n" +
                            "	<dd><a href='/manual/en/features.dtrace.php'>DTrace Dynamic Tracing</a></dd>\n" +
                            "</dl>\n" +
                            "<dl>\n" +
                            "<dt><a href='/manual/en/funcref.php'>Function Reference</a></dt>\n" +
                            "	<dd><a href='/manual/en/refs.basic.php.php'>Affecting PHP's Behaviour</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.utilspec.audio.php'>Audio Formats Manipulation</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.remote.auth.php'>Authentication Services</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.utilspec.cmdline.php'>Command Line Specific Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.compression.php'>Compression and Archive Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.creditcard.php'>Credit Card Processing</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.crypto.php'>Cryptography Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.database.php'>Database Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.calendar.php'>Date and Time Related Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.fileprocess.file.php'>File System Related Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.international.php'>Human Language and Character Encoding Support</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.utilspec.image.php'>Image Processing and Generation</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.remote.mail.php'>Mail Related Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.math.php'>Mathematical Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.utilspec.nontext.php'>Non-Text MIME Output</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.fileprocess.process.php'>Process Control Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.basic.other.php'>Other Basic Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.remote.other.php'>Other Services</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.search.php'>Search Engine Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.utilspec.server.php'>Server Specific Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.basic.session.php'>Session Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.basic.text.php'>Text Processing</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.basic.vartype.php'>Variable and Type Related Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.webservice.php'>Web Services</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.utilspec.windows.php'>Windows Only Extensions</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.xml.php'>XML Manipulation</a></dd>\n" +
                            "	<dd><a href='/manual/en/refs.ui.php'>GUI Extensions</a></dd>\n" +
                            "</dl>\n" +
                            "<dl>\n" +
                            "<dt>Keyboard Shortcuts</dt><dt>?</dt>\n" +
                            "<dd>This help</dd>\n" +
                            "<dt>j</dt>\n" +
                            "<dd>Next menu item</dd>\n" +
                            "<dt>k</dt>\n" +
                            "<dd>Previous menu item</dd>\n" +
                            "<dt>g p</dt>\n" +
                            "<dd>Previous man page</dd>\n" +
                            "<dt>g n</dt>\n" +
                            "<dd>Next man page</dd>\n" +
                            "<dt>G</dt>\n" +
                            "<dd>Scroll to bottom</dd>\n" +
                            "<dt>g g</dt>\n" +
                            "<dd>Scroll to top</dd>\n" +
                            "<dt>g h</dt>\n" +
                            "<dd>Goto homepage</dd>\n" +
                            "<dt>g s</dt>\n" +
                            "<dd>Goto search<br>(current page)</dd>\n" +
                            "<dt>/</dt>\n" +
                            "<dd>Focus search box</dd>\n" +
                            "</dl></div></nav>\n" +
                            "<div id=\"goto\">\n" +
                            "    <div class=\"search\">\n" +
                            "         <div class=\"text\"></div>\n" +
                            "         <div class=\"results\"><ul></ul></div>\n" +
                            "   </div>\n" +
                            "</div>\n" +
                            "\n" +
                            "  <div id=\"breadcrumbs\" class=\"clearfix\">\n" +
                            "    <div id=\"breadcrumbs-inner\">\n" +
                            "          <div class=\"next\">\n" +
                            "        <a href=\"tutorial.requirements.php\">\n" +
                            "          Le n&eacute;cessaire &raquo;\n" +
                            "        </a>\n" +
                            "      </div>\n" +
                            "              <div class=\"prev\">\n" +
                            "        <a href=\"intro-whatcando.php\">\n" +
                            "          &laquo; Que peut faire PHP ?        </a>\n" +
                            "      </div>\n" +
                            "          <ul>\n" +
                            "            <li><a href='index.php'>Manuel PHP</a></li>      <li><a href='getting-started.php'>Au moment de commencer</a></li>      </ul>\n" +
                            "    </div>\n" +
                            "  </div>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "<div id=\"layout\" class=\"clearfix\">\n" +
                            "  <section id=\"layout-content\">\n" +
                            "  <div class=\"page-tools\">\n" +
                            "    <div class=\"change-language\">\n" +
                            "      <form action=\"/manual/change.php\" method=\"get\" id=\"changelang\" name=\"changelang\">\n" +
                            "        <fieldset>\n" +
                            "          <label for=\"changelang-langs\">Change language:</label>\n" +
                            "          <select onchange=\"document.changelang.submit()\" name=\"page\" id=\"changelang-langs\">\n" +
                            "            <option value='en/tutorial.php'>English</option>\n" +
                            "            <option value='pt_BR/tutorial.php'>Brazilian Portuguese</option>\n" +
                            "            <option value='zh/tutorial.php'>Chinese (Simplified)</option>\n" +
                            "            <option value='fr/tutorial.php' selected=\"selected\">French</option>\n" +
                            "            <option value='de/tutorial.php'>German</option>\n" +
                            "            <option value='ja/tutorial.php'>Japanese</option>\n" +
                            "            <option value='kr/tutorial.php'>Korean</option>\n" +
                            "            <option value='ro/tutorial.php'>Romanian</option>\n" +
                            "            <option value='ru/tutorial.php'>Russian</option>\n" +
                            "            <option value='es/tutorial.php'>Spanish</option>\n" +
                            "            <option value='tr/tutorial.php'>Turkish</option>\n" +
                            "            <option value=\"help-translate.php\">Other</option>\n" +
                            "          </select>\n" +
                            "        </fieldset>\n" +
                            "      </form>\n" +
                            "    </div>\n" +
                            "    <div class=\"edit-bug\">\n" +
                            "      <a href=\"https://edit.php.net/?project=PHP&amp;perm=fr/tutorial.php\">Edit</a>\n" +
                            "      <a href=\"https://bugs.php.net/report.php?bug_type=Documentation+problem&amp;manpage=tutorial\">Report a Bug</a>\n" +
                            "    </div>\n" +
                            "  </div><div id=\"tutorial\" class=\"chapter\">\n" +
                            "  <h1>Une introduction à PHP</h1>\n" +
                            "<h2>Sommaire</h2><ul class=\"chunklist chunklist_chapter\"><li><a href=\"tutorial.requirements.php\">Le n&eacute;cessaire</a></li><li><a href=\"tutorial.firstpage.php\">Votre premi&egrave;re page PHP</a></li><li><a href=\"tutorial.useful.php\">Trucs pratiques</a></li><li><a href=\"tutorial.forms.php\">Utiliser un formulaire</a></li><li><a href=\"tutorial.oldcode.php\">Utiliser des codes anciens avec les nouvelles versions de PHP</a></li><li><a href=\"tutorial.whatsnext.php\">Et apr&egrave;s ?</a></li></ul>\n" +
                            "\n" +
                            "\n" +
                            "  <p class=\"para\">\n" +
                            "   Dans cette section, nous voulons illustrer les principes de base\n" +
                            "   de PHP dans une courte introduction. Ce chapitre traite uniquement\n" +
                            "   de création de pages web dynamiques avec PHP, laissant de coté\n" +
                            "   temporairement les autres possibilités de PHP. Voyez la section\n" +
                            "   <a href=\"intro-whatcando.php\" class=\"link\">Ce que peut faire PHP</a> pour\n" +
                            "   plus d&#039;informations.\n" +
                            "  </p>\n" +
                            "  <p class=\"para\">\n" +
                            "   Les pages web qui exploitent PHP sont traitées comme des pages\n" +
                            "   HTML standards, et vous pouvez les créer, éditer et effacer\n" +
                            "   tout comme vous le faites normalement avec des pages HTML\n" +
                            "   classiques.\n" +
                            "  </p>\n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "  \n" +
                            " </div>\n" +
                            "\n" +
                            "<section id=\"usernotes\">\n" +
                            " <div class=\"head\">\n" +
                            "  <span class=\"action\"><a href=\"/manual/add-note.php?sect=tutorial&amp;redirect=http://php.net/manual/fr/tutorial.php\"><img src='/images/notes-add@2x.png' alt='add a note' width='12' height='12'> <small>add a note</small></a></span>\n" +
                            "  <h3 class=\"title\">User Contributed Notes </h3>\n" +
                            " </div>\n" +
                            " <div class=\"note\">There are no user contributed notes for this page.</div></section>    </section><!-- layout-content -->\n" +
                            "        <aside class='layout-menu'>\n" +
                            "    \n" +
                            "        <ul class='parent-menu-list'>\n" +
                            "                                    <li>\n" +
                            "                <a href=\"getting-started.php\">Au moment de commencer</a>\n" +
                            "    \n" +
                            "                                    <ul class='child-menu-list'>\n" +
                            "    \n" +
                            "                          \n" +
                            "                        <li class=\"\">\n" +
                            "                            <a href=\"introduction.php\" title=\"Introduction\">Introduction</a>\n" +
                            "                        </li>\n" +
                            "                          \n" +
                            "                        <li class=\"current\">\n" +
                            "                            <a href=\"tutorial.php\" title=\"Une introduction &agrave; PHP\">Une introduction &agrave; PHP</a>\n" +
                            "                        </li>\n" +
                            "                            \n" +
                            "                    </ul>\n" +
                            "                    \n" +
                            "            </li>\n" +
                            "                        \n" +
                            "                    </ul>\n" +
                            "    </aside>\n" +
                            "\n" +
                            "\n" +
                            "  </div><!-- layout -->\n" +
                            "         \n" +
                            "  <footer>\n" +
                            "    <div class=\"container footer-content\">\n" +
                            "      <div class=\"row-fluid\">\n" +
                            "      <ul class=\"footmenu\">\n" +
                            "        <li><a href=\"/copyright.php\">Copyright &copy; 2001-2016 The PHP Group</a></li>\n" +
                            "        <li><a href=\"/my.php\">My PHP.net</a></li>\n" +
                            "        <li><a href=\"/contact.php\">Contact</a></li>\n" +
                            "        <li><a href=\"/sites.php\">Other PHP.net sites</a></li>\n" +
                            "        <li><a href=\"/mirrors.php\">Mirror sites</a></li>\n" +
                            "        <li><a href=\"/privacy.php\">Privacy policy</a></li>\n" +
                            "      </ul>\n" +
                            "      </div>\n" +
                            "    </div>\n" +
                            "  </footer>\n" +
                            "\n" +
                            "    \n" +
                            " <!-- External and third party libraries. -->\n" +
                            " <script type=\"text/javascript\" src=\"//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
                            "<script type=\"text/javascript\" src=\"http://php.net/cached.php?t=1421837618&amp;f=/js/ext/modernizr.js\"></script>\n" +
                            "<script type=\"text/javascript\" src=\"http://php.net/cached.php?t=1421837618&amp;f=/js/ext/hogan-2.0.0.min.js\"></script>\n" +
                            "<script type=\"text/javascript\" src=\"http://php.net/cached.php?t=1421837618&amp;f=/js/ext/typeahead.min.js\"></script>\n" +
                            "<script type=\"text/javascript\" src=\"http://php.net/cached.php?t=1421837618&amp;f=/js/ext/mousetrap.min.js\"></script>\n" +
                            "<script type=\"text/javascript\" src=\"http://php.net/cached.php?t=1421837618&amp;f=/js/search.js\"></script>\n" +
                            "<script type=\"text/javascript\" src=\"http://php.net/cached.php?t=1478627406&amp;f=/js/common.js\"></script>\n" +
                            "\n" +
                            "<a id=\"toTop\" href=\"javascript:;\"><span id=\"toTopHover\"></span><img width=\"40\" height=\"40\" alt=\"To Top\" src=\"/images/to-top@2x.png\"></a>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>\n" +
                            ""
                        )).execute().actionGet();
       
     
     
GetResponse getResponse = client.prepareGet("recherche1", "pageweb", "1").execute().actionGet();

Map<String, Object> source = getResponse.getSource();
System.out.println("------------------------------");
System.out.println("Index: " + getResponse.getIndex());
System.out.println("Type: " + getResponse.getType());
System.out.println("Id: " + getResponse.getId());
System.out.println("Version: " + getResponse.getVersion());
System.out.println(source);
System.out.println("------------------------------"); 
    

	}
        
    public static Map<String, Object> putJsonDocument(String titre, String description, String url, int pertinence, String mot_cle, String code_source)
    {
            Map<String, Object> jsonDocument = new HashMap<String, Object>();
            jsonDocument.put("titre", titre);
            jsonDocument.put("desccription", description);
            jsonDocument.put("url", url);
            jsonDocument.put("pertinence",pertinence );
            jsonDocument.put("mot_cle",mot_cle );
            jsonDocument.put("code_source",code_source );

           
            return jsonDocument;
    }
}
