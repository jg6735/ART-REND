const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
	transpileDependencies: true,
	// devServer: {
	// 	proxy: {
	// 		'/auth-service': {
	// 			target: 'http://localhost:8080/',
	// 			changeOrigin: true,
	// 			logLevel: 'debug',
	// 		},
	// 		'/business-service': {
	// 			target: 'http://localhost:8080/',
	// 			pathRewrite: { '^/detail': '/', '^/mypage': '/', '^/artist': '/' },
	// 			changeOrigin: true,
	// 			logLevel: 'debug',
	// 		},
	// 	},
	// },
})
