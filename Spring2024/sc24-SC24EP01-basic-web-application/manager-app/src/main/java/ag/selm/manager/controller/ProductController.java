package ag.selm.manager.controller;

import ag.selm.manager.controller.model.UpdateProductModel;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") Integer productId) {
        return productService.findProductById(productId).orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping()
    public String getProductById() {
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String editProduct() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String editProduct(@ModelAttribute Product product, @Valid UpdateProductModel updateModel, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", product);
            model.addAttribute("errors", bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());

            return "catalogue/products/edit";
        } else {
            productService.updateProduct(product.getId(), updateModel.title(), updateModel.details());

            return "redirect:/catalogue/products/%d".formatted(product.getId());
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute Product product) {
        productService.deleteProduct(product.getId());

        return "redirect:/catalogue/products/list";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException e, Model model, Locale locale) {
        model.addAttribute(
                "error",
                messageSource.getMessage(e.getMessage(), new Object[0], e.getMessage(), locale)
        );

        return "errors/404";
    }
}
