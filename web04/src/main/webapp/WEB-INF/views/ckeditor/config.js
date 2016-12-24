/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';

	//1.모양을 적용하기 위해 위젯 설치
	//먼저 lineutils 와  widgetselection 설치 
	config.extraPlugins = 'widget';
	
	//먼저 widget 설치 부트스트랩 용 
	config.extraPlugins = 'btgrid';
	
	//부트스트랩 용
	config.extraPlugins = 'widgetbootstrap';


	
/*	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';
	
*/
	//코드 hightler
	
	config.extraPlugins = 'codesnippet';
	//config.extraPlugins = 'youtube';
	


	
};
